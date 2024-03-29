app.controller('AccountCtrl', function ($scope, $routeParams, $window, AccountService, AssetsService) {
  var accounts = $.cookie('mkb.accounts');
  if(accounts) {
    accounts = accounts.split(',');
  } else {
    accounts = [];
  }

  var me = {
    accounts: accounts,
    username: $routeParams.username || $.cookie('mkb.username'),
    password: '',
    remember: true,
    maxTry: 5,

    friends: [],
    cards: [],

    getAssets: function () {
//      me.mapDefs = AssetsService.getMapDefs();
//      me.skillDefs = AssetsService.getSkillDefs();
      AssetsService.getCardDefs(function (cardDefs) {
        me.cardDefsGrid = new AssetsCardDefsGrid(cardDefs);
      });
//      me.runeDefs = AssetsService.getRuneDefs();
    },

    refreshAssets: function (username) {
      AssetsService.refreshAssets(username, function () {
        me.getAssets();
      });
    },

    login: function () {
      AccountService.login(me.username, me.password, false, function (user) {
        me.switch = me.username;
        if(me.remember) {
          $.cookie('mkb.username', me.username, {expires: 7});
          if($.inArray(me.username, me.accounts) == -1) {
            me.accounts.push(me.username);
            $.cookie('mkb.accounts', me.accounts, {expires: 7});
          }
        }
        me.getAssets();
        me.user = user;

        AccountService.getFriends(me.username, false, function (friends) {
          me.friends = friends;
          me.userFriendsGrid = new UserFriendsGrid(friends);
        });
        AccountService.getCards(me.username, false, function (cards) {
          me.cards = cards;
          AssetsService.getCardDefs(function (cardDefs) {
            me.userCardsGrid = new UserCardGrid(cards, cardDefs);
          });
        });
      });
    },

    switchAccount: function () {
      me.username = me.switch;
      me.login();
    },

    removeAccount: function () {
      me.accounts = $.grep(me.accounts, function (value) {
        return value != me.switch;
      });
      $.cookie('mkb.accounts', me.accounts, {expires: 7});
      if(me.switch != me.username) {
        me.switch = me.username;
      } else {
        me.switch = '';
        $.removeCookie('mkb.username');
      }
    },

    logout: function () {
      delete me.user;
      $.removeCookie('mkb.username');
    },

    refreshUserInfo: function (remote) {
      AccountService.refreshUserInfo(me.username, remote, function (user) {
        me.user = user;
      });
    },

    refreshUserCard: function (remote) {
      AccountService.getCards(me.username, remote, function (cards) {
        me.cards = cards;
      });
    },

    getMazeStatus: function () {
      if(!me.mazeStatus) {
        AccountService.getMazeStatus(me.username, function (status) {
          me.mazeStatus = status;
        });
      }
    },

    resetMaze: function (id) {
      AccountService.resetMaze(me.username, id, function (status) {
        me.mazeStatus[id] = status;
      });
    },

    refreshMaze: function (id, remote) {
      AccountService.refreshMaze(me.username, id, remote, function (status) {
        me.mazeStatus[id] = status;
      })
    },

    clearMaze: function (id) {
      AccountService.clearMaze(me.username, id, me.maxTry, function (status) {
        me.mazeStatus[id] = status;
        me.refreshUserInfo(false);
      });
    },

    readCard: function (info, cardDefs) {
      var cards = info.split(',');
      var ret = [];
      $.each(cards, function (index, value) {
        var detail = value.split('_');
        ret.push(detail[1] + '��' + cardDefs[detail[0]].cardName);
      });
      return ret;
    },

    updateCounterAttackDetails: function(counterAttacks) {
      me.attackDetails = [];
      AssetsService.getMapStageDefs(function (mapStageDefs) {
        AssetsService.getCardDefs(function (cardDefs) {
          $.each(counterAttacks, function (index, attack) {
            if(attack.counterAttackTime) {
              var stageDef = mapStageDefs[attack.mapStageDetailId];
              var levelDef = stageDef.levels[attack.finishedStage - 1];
              me.attackDetails.push({
                stageId: attack.mapStageDetailId,
                clear: true,
                id: attack.mapStageId + '-' + (stageDef.rank + 1) + '-' + attack.finishedStage,
                stageName: stageDef.name,
                enemyLevel: levelDef.heroLevel,
                cards: me.readCard(levelDef.cardList, cardDefs)
              });
            }
          });
        });
      });
    },

    getCounterAttacks: function () {
      AccountService.getCounterAttacks(me.username, function (counterAttacks) {
        me.counterAttacks = counterAttacks;
        me.updateCounterAttackDetails(me.counterAttacks);
      });
    },

    clearCounterAttacks: function () {
      var stageIds = [];
      $.each(me.attackDetails, function (index, value) {
        if(value.clear) {
          stageIds.push(value.stageId);
        }
      });
      AccountService.clearCounterAttacks(me.username, stageIds, me.maxTry, function (response) {
        me.counterAttacks = response.userMapStages;
        me.updateCounterAttackDetails(me.counterAttacks);
      });
    },

    getGoodsList: function () {
    }

  };

  if(me.username) {
    me.login();
  }

  $scope.account = me;

  // Set up view resize
  var view = $('div.mkb-view');
  var resize = function () {
    view.height($(window).height() - (view.offset().top + 40));
  };
  angular.element($window).bind('resize', resize);
  resize();
});


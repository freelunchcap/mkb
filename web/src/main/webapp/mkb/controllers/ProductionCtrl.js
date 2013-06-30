app.controller('AutomatedProductionCtrl', function($scope, $timeout, ProductionService, AssetsService) {

  var me = {
    cardFilters: {},

    getAssets: function() {
//      me.mapDefs = AssetsService.getMapDefs();
//      me.skillDefs = AssetsService.getSkillDefs();
      me.cardDefs = AssetsService.getCardDefs();
//      me.runeDefs = AssetsService.getRuneDefs();
    },

    addFilter: function() {
      me.cardFilters[me.cardId] = me.cardNumber;
    },

    removeFilter: function(cardId) {
      delete me.cardFilters[cardId];
    },

    clearFilters: function() {
      me.cardFilters = {};
    },

    readFilter: function(cardId, cardNumber) {
      return cardNumber + '张' + me.cardDefs[cardId].cardName;
    },

    hasFilter: function() {
      return !$.isEmptyObject(me.cardFilters);
    },

    refreshCriterion: function() {
      ProductionService.getProductionCriterion(function(criterion) {
        if(me.criterionGrid) {
          me.criterionGrid.data = criterion;
        } else {
          AssetsService.getCardDefs(function(cardDefs) {
            me.criterionGrid = new CriterionGrid(criterion, cardDefs);
          });
        }
      });
    },

    addCriteria: function() {
      ProductionService.addProductionCriteria(me.cardFilters, function(criterion) {
        me.criterionGrid.data = criterion;
      });
    },

    getServers: function() {
      ProductionService.getServers(function(servers) {
        me.servers = servers;
      })
    },

    produceAccounts: function() {
      ProductionService.produceAccounts({
        serverId: me.serverId,
        usernamePrefix: me.usernamePrefix,
        nicknamePrefix: me.nicknamePrefix,
        password: me.password,
        gender: me.gender,
        total: me.total,
        inviteCode: me.inviteCode,
        useSameInviteCode: me.useSameInviteCode
      }, function() {
        me.productionProgressBar = {
          active: true
        };
        $timeout(me.getProductionUpdate, 2000);
      });
    },

    getProductionUpdate: function() {
      ProductionService.getProductionUpdate(function(progress) {
        var accounts = progress.accounts;
        var total = progress.total;
        me.productionProgressBar.setPercentage(accounts.length / total);
        if(progress.finish) {
          me.productionProgressBar.setActive(false);
          if(accounts.length == total) {
            me.productionProgressBar.setSuccess();
          } else {
            me.productionProgressBar.setWarning();
          }
        } else {
          $timeout(me.getProductionUpdate, 2000);
        }
      });
    }


  };

  me.getAssets();
  me.refreshCriterion();
  me.getServers();

  $scope.production = me;
});
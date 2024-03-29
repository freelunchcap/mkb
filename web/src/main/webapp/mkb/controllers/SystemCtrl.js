app.controller('SystemCtrl', function($scope, $location, $window, SystemService, AssetsService) {

  var readCompareOperator = function(operator) {
    switch(operator) {
      case 'GreaterThan': return '大于';
      case 'GreaterThanOrEqualTo': return '大于等于';
      case 'EqualTo': return '等于';
      case 'NotEqualTo': return '不等于';
      case 'LessThan': return '小于';
      case 'LessThanOrEqualTo': return '小于等于';
    }
    return '未知对比';
  };

  var readCurrency = function(currency) {
    switch(currency) {
      case 'Coins': return '金币';
      case 'Cash': return '晶钻';
      case 'Ticket': return '魔幻券';
    }
    return '未知货币';
  };

  var createGridData = function(pool) {
    var ret = [];
    var usernames = pool.usernames || [];
    var custom = pool.customSettings || {};
    $.each(usernames, function(index, username) {
      var settings = custom[username] || pool.defaultSettings;
      ret.push($.extend({username: username}, settings));
    });
    return ret;
  };

  var me = {
    accountFilters: [],

    getAssets: function() {
//      me.mapDefs = AssetsService.getMapDefs();
//      me.skillDefs = AssetsService.getSkillDefs();
      me.cardDefs = AssetsService.getCardDefs();
//      me.runeDefs = AssetsService.getRuneDefs();
    },

    getSelectedUsers: function(grid) {
      if(!grid) grid = me.accountsGrid;
      var selected = grid.getSelectedRows();
      var usernames = [];
      $.each(selected, function(index, value) {
        usernames.push(value.username);
      });
      return usernames;
    },

    refreshAccounts: function() {
      SystemService.findAccounts(me.accountFilters, function(accounts) {
        me.accountsGrid = new AccountsGrid(accounts);
      });
    },

    addBoss: function() {
      SystemService.addBossPool(me.getSelectedUsers(), function(bossPool) {
        me.bossGrid.data = createGridData(bossPool);
      });
    },

    refreshBoss: function() {
      SystemService.getBossPool(function(bossPool) {
        me.bossPool = bossPool;
        me.bossGrid = new BossGrid(createGridData(bossPool));
      });
    },

    removeBoss: function() {
      var usernames = me.getSelectedUsers(me.bossGrid);
      SystemService.removeBossPool(usernames, function(bossPool) {
        me.bossGrid.data = createGridData(bossPool);
      });
    },

    addMap: function() {
      SystemService.addMapPool(me.getSelectedUsers(), function(mapPool) {
        me.mapGrid.data = createGridData(mapPool);
      });
    },

    refreshMap: function() {
      SystemService.getMapPool(function(mapPool) {
        me.mapPool = mapPool;
        me.mapGrid = new MapGrid(createGridData(mapPool));
      });
    },

    removeMap: function() {
      var usernames = me.getSelectedUsers(me.mapGrid);
      SystemService.removeMapPool(usernames, function(mapPool) {
        me.mapGrid.data = createGridData(mapPool);
      });
    },

    addMaze: function() {
      SystemService.addMazePool(me.getSelectedUsers(), function(mazePool) {
        me.mazeGrid.data = createGridData(mazePool);
      });
    },

    refreshMaze: function() {
      SystemService.getMazePool(function(mazePool) {
        me.mazePool = mazePool;
        me.mazeGrid = new MazeGrid(createGridData(mazePool));
      });
    },

    removeMaze: function() {
      var usernames = me.getSelectedUsers(me.mazeGrid);
      SystemService.removeMazePool(usernames, function(mazePool) {
        me.mazeGrid.data = createGridData(mazePool);
      });
    },

    addFenergy: function() {
      SystemService.addFenergyPool(me.getSelectedUsers(), function(fenergyPool) {
        me.fenergyGrid.data = createGridData(fenergyPool);
      });
    },

    refreshFenergy: function() {
      SystemService.getFenergyPool(function(fenergyPool) {
        me.fenergyPool = fenergyPool;
        me.fenergyGrid = new FenergyGrid(createGridData(fenergyPool));
      });
    },

    removeFenergy: function() {
      var usernames = me.getSelectedUsers(me.fenergyGrid);
      SystemService.removeFenergyPool(usernames, function(fenergyPool) {
        me.fenergyGrid.data = createGridData(fenergyPool);
      });
    },

    addLegion: function() {
      SystemService.addLegionPool(me.getSelectedUsers(), function(legionPool) {
        me.legionGrid.data = createGridData(legionPool);
      });
    },

    refreshLegionPool: function() {
      SystemService.getLegionPool(function(legionPool) {
        me.legionPool = legionPool;
        me.legionGrid = new LegionGrid(createGridData(legionPool));
      });
    },

    removeLegion: function() {
      var usernames = me.getSelectedUsers(me.legionGrid);
      SystemService.removeLegionPool(usernames, function(legionPool) {
        me.legionGrid.data = createGridData(legionPool);
      });
    },

    addFriends: function() {
      SystemService.addFriendsPool(me.getSelectedUsers(), function(friendsPool) {
        me.friendsGrid.data = createGridData(friendsPool);
      });
    },

    removeFriends: function() {
      var usernames = me.getSelectedUsers(me.friendsGrid);
      SystemService.removeFriendsPool(usernames, function(friendsPool) {
        me.friendsGrid.data = createGridData(friendsPool);
      });
    },

    refreshFriends: function() {
      SystemService.getFriendsPool(function(friendsPool) {
        me.friendsPool = friendsPool;
        me.friendsGrid = new FriendsGrid(createGridData(friendsPool));
      });
    },

    addFilter: function() {
      var filter = {type: me.search};
      if(me.search == 'level') {
        filter.compare = me.levelCompare;
        filter.value = me.levelValue
      } else if(me.search == 'card') {
        filter.id = me.cardId;
        filter.number = me.cardNumber;
      } else if(me.search == 'currency') {
        filter.currency = me.currencyType;
        filter.compare = me.currencyCompare;
        filter.value = me.currencyValue;
      } else if(me.search == 'legion') {
        filter.name = me.legionName;
      }
      me.accountFilters.push(filter);
    },

    readFilter: function(filter) {
      var ret = '';
      switch(filter.type) {
        case 'level':
          ret += '等级';
          ret += readCompareOperator(filter.compare);
          ret += filter.value;
          break;
        case 'card':
          ret += '拥有';
          ret += filter.number + '张';
          ret += me.cardDefs[filter.id].cardName;
          break;
        case 'currency':
          ret += '拥有';
          ret += readCompareOperator(filter.compare);
          ret += filter.value;
          ret += readCurrency(filter.currency);
          break;
        case 'legion':
          ret += '属于名字中带有“';
          ret += filter.name;
          ret += '”的军团';
          break;
      }
      return ret;
    },

    removeFilter: function(filter) {
      me.accountFilters = $.grep(me.accountFilters, function(value) {
        return value != filter;
      });
    },

    clearFilters: function() {
      me.accountFilters = [];
    },

    viewAccount: function() {
      var selected = me.accountsGrid.getSelectedRows();
      if(selected.length) {
        $location.path('/account').search({username: selected[0].username});
      }
    }
  };

  me.getAssets();
  me.refreshAccounts();
  me.refreshBoss();
  me.refreshMap();
  me.refreshMaze();
  me.refreshFenergy();
  me.refreshLegionPool();
  me.refreshFriends();

  $scope.system = me;

  // Set up view resize
  var view = $('div.mkb-view');
  var resize = function() {
    view.height($(window).height() - (view.offset().top + 40));
  };
  angular.element($window).bind('resize', resize);
  resize();
});
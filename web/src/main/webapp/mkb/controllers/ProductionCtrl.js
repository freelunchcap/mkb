app.controller('AutomatedProductionCtrl', function($scope, ProductionService, AssetsService) {

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
    }


  };

  me.getAssets();
  me.refreshCriterion();

  $scope.production = me;
});
function RaceFormatter() {
  return function(value) {
    switch(value) {
      case 1: return '王国';
      case 2: return '森林';
      case 3: return '蛮荒';
      case 4: return '地狱';
    }
    return '魔神';
  }
}

function CardNameFormatter(cardDefs) {
  return function(cardId) {
    return cardDefs[cardId].cardName;
  }
}

function CardCountFormatter(cardDefs) {
  return function(cardCount) {
    var ret = '';
    $.each(cardCount, function(cardId, count) {
      if(ret) ret += '，';
      ret += count + '张' + cardDefs[cardId].cardName;
    });
    return ret;
  }
}


function RankFightResultFormatter() {
  return function(value, options, record) {
    var ret = record.win + '/' + record.lose;
    var total = record.win + record.lose;
    if(total) {
      ret += ' ' + (record.win / total * 100).toFixed(2) + '%';
    }
    return ret;
  }
}

function NicknameFormatter() {
  return function(userInfo) {
    if(userInfo) {
      return userInfo.nickName;
    }
    return '未知昵称';
  }
}

function NewCardsFormatter(cardDefs) {
  return function(cardIds) {
    var ret = '';
    $.each(cardIds, function(index, cardId) {
      if(ret) ret += '，';
      ret += cardDefs[cardId].cardName;
    });
    return ret;
  }
}
package im.grusis.mkb.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import im.grusis.mkb.emulator.emulator.core.model.basic.*;
import im.grusis.mkb.internal.CardAssets;
import im.grusis.mkb.internal.RuneAssets;
import im.grusis.mkb.internal.SkillAssets;
import im.grusis.mkb.repository.AssetsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * User: Mothership
 * Date: 13-6-5
 * Time: 下午8:29
 */
@Service
public class AssetsService {

  private AssetsRepository assetsRepository;

  private Map<Integer, Card> cardLookup = new LinkedHashMap<Integer, Card>();
  private Map<Integer, Rune> runeLookup = new LinkedHashMap<Integer, Rune>();
  private Map<Integer, Skill> skillLookup = new LinkedHashMap<Integer, Skill>();

  @Autowired
  public AssetsService(AssetsRepository assetsRepository) {
    this.assetsRepository = assetsRepository;
    CardAssets cardAssets = assetsRepository.getAssets(CardAssets.AssetName, CardAssets.class);
    updateCardLookup(cardAssets);
    RuneAssets runeAssets = assetsRepository.getAssets(RuneAssets.AssetName, RuneAssets.class);
    updateRuneLookup(runeAssets);
    SkillAssets skillAssets = assetsRepository.getAssets(SkillAssets.AssetName, SkillAssets.class);
    updateSkillLookup(skillAssets);
  }

  public void updateCardLookup(CardAssets cardAssets) {
    if(cardAssets == null) {
      return;
    }
    cardLookup.clear();
    AllCard cards = cardAssets.getAsset();
    List<Card> cardList = cards.getCards();
    for(Card card : cardList) {
      cardLookup.put(card.getCardId(), card);
    }
  }

  public void updateRuneLookup(RuneAssets runeAssets) {
    if(runeAssets == null) {
      return;
    }
    runeLookup.clear();
    Runes runes = runeAssets.getAsset();
    List<Rune> runeList = runes.getRunes();
    for(Rune rune : runeList) {
      runeLookup.put(rune.getRuneId(), rune);
    }
  }

  public void updateSkillLookup(SkillAssets skillAssets) {
    if(skillAssets == null) {
      return;
    }
    skillLookup.clear();
    AllSkill skills = skillAssets.getAsset();
    List<Skill> skillList = skills.getSkills();
    for(Skill skill : skillList) {
      skillLookup.put(skill.getSkillId(), skill);
    }
  }

  public Card findCard(int id) {
    return cardLookup.get(id);
  }

  public Rune findRune(int id) {
    return runeLookup.get(id);
  }

  public Skill findSkill(int id) {
    return skillLookup.get(id);
  }

  public void saveAssets(AllCard cards) {
    CardAssets cardAssets = new CardAssets();
    cardAssets.setAsset(cards);
    assetsRepository.createOrUpdateAssets(cardAssets);
    updateCardLookup(cardAssets);
  }

  public void saveAssets(Runes runes) {
    RuneAssets runeAssets = new RuneAssets();
    runeAssets.setAsset(runes);
    assetsRepository.createOrUpdateAssets(runeAssets);
    updateRuneLookup(runeAssets);
  }

  public void saveAssets(AllSkill skills) {
    SkillAssets skillAssets = new SkillAssets();
    skillAssets.setAsset(skills);
    assetsRepository.createOrUpdateAssets(skillAssets);
    updateSkillLookup(skillAssets);
  }
}

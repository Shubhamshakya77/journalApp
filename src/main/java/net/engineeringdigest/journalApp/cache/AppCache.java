package net.engineeringdigest.journalApp.cache;

import jakarta.annotation.PostConstruct;
import net.engineeringdigest.journalApp.entity.ConfigJournalEntity;
import net.engineeringdigest.journalApp.repository.ConfigJournalAppRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCache {

    public enum keys {
        WEATHER_API;

    }

    @Autowired
  private ConfigJournalAppRepository configJournalAppRepository;

    public Map<String,String> appCache;


   @PostConstruct
           public void init() {
       appCache=new HashMap<>();

       List<ConfigJournalEntity> all = configJournalAppRepository.findAll();
       for (ConfigJournalEntity configJournalEntity:all){
           appCache.put(configJournalEntity.getKey(), configJournalEntity.getValue());
       }



   }

}

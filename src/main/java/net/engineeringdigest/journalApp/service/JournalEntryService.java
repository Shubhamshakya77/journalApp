package net.engineeringdigest.journalApp.service;

import lombok.extern.slf4j.Slf4j;
import net.engineeringdigest.journalApp.entity.JournalEntry;
import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepo;

    @Autowired
    private UserService userService;

    @Transactional
    public void saveEntry(JournalEntry journalEntry, String userName){
       try {
           User user =userService.findByUserName(userName);
           journalEntry.setDate(LocalDateTime.now());
           JournalEntry saved = journalEntryRepo.save(journalEntry);
           user.getJournalEntries().add(saved);
           userService.saveUser(user);
       }catch (Exception e){
          // System.out.println(e);
           throw new RuntimeException("An error occurred while saving the entry" ,e);
       }
    }
    public void saveEntry(JournalEntry journalEntry ) {
        journalEntryRepo.save(journalEntry);
    }
    public List<JournalEntry>getAll(){
        return journalEntryRepo.findAll();
    }
    public Optional<JournalEntry> findByid(ObjectId id){
        return journalEntryRepo.findById(id);
    }
    public boolean deleteById(ObjectId id, String userName){
        boolean removed=false;
        try {
            User user =userService.findByUserName(userName);
             removed = user.getJournalEntries().removeIf(x -> x.getId().equals(id));
            if (removed){
                userService.saveUser(user);
                journalEntryRepo.deleteById(id);

            }


        }catch (Exception e){
            log.error("Error" ,e);
            throw new RuntimeException("An error occurred while deleting the entry" ,e);
        }
        return removed;


    }


}

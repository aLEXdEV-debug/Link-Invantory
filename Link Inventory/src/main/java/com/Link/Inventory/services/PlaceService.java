package com.Link.Inventory.services;

import com.Link.Inventory.controllers.models.Task;
import com.Link.Inventory.controllers.models.User;
import com.Link.Inventory.repositories.PlaceRepository;
import com.Link.Inventory.repositories.UserRepository;
import com.Link.Inventory.controllers.models.Place;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.HashMap;

@Service
@Slf4j
@RequiredArgsConstructor
public class PlaceService {

    private final PlaceRepository placeRepository;

    private final UserRepository userRepository;

    public void savePlace(Principal principal, HashMap formData) {
        Place place = new Place();
        place.setUser(getUserByPrincipal(principal));
        var valuesForm = formData.values().iterator();
        place.setTitle(valuesForm.next().toString());
        place.setOrganization(valuesForm.next().toString());
        while (valuesForm.hasNext()) {
            String next = valuesForm.next().toString();
            if (next.isEmpty())
                break;
            Task task = new Task();
            task.setCharacteristic(next);
            task.setId(valuesForm.next().toString());
            place.addTaskToPlace(task);
        }
        placeRepository.save(place);
        log.info("Saving new Place.");
    }

    public void deletePlace(Long id){
        placeRepository.deleteById(id);
    }

    public void invertPlace(Long id){
        Place invert = getPlaceById(id);
        if (invert.isChecked()){
            invert.setChecked(false);
        }
        else {
            invert.setChecked(true);
        }
        placeRepository.save(invert);
    }

    public void invertAccept(Long id, HashMap formData){
        var place = getPlaceById(id);
        int i=1;
        for (var itVar : place.getTasks())
        {
            itVar.setAccounting(formData.containsKey("accounting_"+i));
            itVar.setFact(formData.containsKey("fact_"+i));
            itVar.setStatus(formData.get("status_"+i).toString());
            i++;
        }
        placeRepository.save(place);
    }

    public List<Place> listPlace(User user) {
        if (user.isDirector()){
            return placeRepository.findByUser(user);
        } else if (user.isWorker()){
            return placeRepository.findByChecked(false);
        }
        return placeRepository.findAll();
    }

    public Place getPlaceById(Long id){
        return placeRepository.getById(id);
    }

    public User getUserByPrincipal(Principal principal) {
        return userRepository.findByEmail(principal.getName());
    }
}
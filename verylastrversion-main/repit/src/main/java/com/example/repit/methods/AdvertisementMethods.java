package com.example.repit.methods;

import com.example.repit.entities.Advertisement;
import com.example.repit.entities.Authentication;
import com.example.repit.repositories.AdvertisementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AdvertisementMethods {
    private final AdvertisementRepository advertisementRepository;
    @Autowired
    public AdvertisementMethods(AdvertisementRepository advertisementRepository){
        this.advertisementRepository = advertisementRepository;
    }

    @Transactional(readOnly = true)
    public List<Advertisement> getAll(){
        return advertisementRepository.findAll();
    }
    @Transactional(readOnly = true)
    public Advertisement getByID(int id){
        Optional<Advertisement> advertisement = advertisementRepository.findById(id);
        return advertisement.orElse(null);
    }
    @Transactional
    public void save(Advertisement advertisement){
        advertisementRepository.save(advertisement);
    }

    @Transactional
    public void update(int id, Advertisement NewAdvertisement){
        Advertisement old = getByID(id);

        old.setSubject(NewAdvertisement.getSubject());
        old.setDuration(NewAdvertisement.getDuration());
        old.setPrice(NewAdvertisement.getPrice());

        advertisementRepository.save(old);
    }

    @Transactional
    public void delete(int id){
        advertisementRepository.deleteById(id);
    }


}

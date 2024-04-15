package tn.esprit.rh.achat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import tn.esprit.rh.achat.entities.CategorieProduit;
import tn.esprit.rh.achat.repositories.CategorieProduitRepository;
import tn.esprit.rh.achat.services.CategorieProduitServiceImpl;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SpringBootappTest {


    @InjectMocks
    private CategorieProduitServiceImpl categorieProduitService;

    @Mock
    private CategorieProduitRepository categorieProduitRepository;

    @Test
    public void getAllTest(){
        Mockito.when(categorieProduitRepository.findAll()).thenReturn(Stream.of(new CategorieProduit(22,"123A","pull")).collect(Collectors.toList()));
        assertEquals(2,categorieProduitService.retrieveAllCategorieProduits().size());
    }

}


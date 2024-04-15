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
import tn.esprit.rh.achat.controllers.CategorieProduitController;
import tn.esprit.rh.achat.entities.CategorieProduit;
import tn.esprit.rh.achat.repositories.CategorieProduitRepository;
import tn.esprit.rh.achat.services.CategorieProduitServiceImpl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class SpringBootappTest {


    @InjectMocks
    private CategorieProduitServiceImpl categorieProduitService;

    @Mock
    private CategorieProduitController categorieProduitController;

    @Test
    public void getAllTest(){
        CategorieProduit categorie1 = new CategorieProduit(1, "Category 1", "Description 1");
        CategorieProduit categorie2 = new CategorieProduit(2, "Category 2", "Description 2");
        List<CategorieProduit> expectedList = Arrays.asList(categorie1, categorie2);

        when(categorieProduitService.retrieveAllCategorieProduits()).thenReturn(expectedList);

        // Act
        List<CategorieProduit> actualList = categorieProduitController.getCategorieProduit();

        // Assert
        assertEquals(expectedList.size(), actualList.size(), "Lists should have the same size");
        for (int i = 0; i < expectedList.size(); i++) {
            assertEquals(expectedList.get(i), actualList.get(i), "Category at index " + i + " should match");
        }

    }

}


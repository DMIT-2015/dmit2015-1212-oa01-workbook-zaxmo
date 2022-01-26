package ca.nait.dmit.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AlbertaCovid19CaseManagerTest {

    AlbertaCovid19CaseManager caseManager;
    @BeforeEach
    void beforeEach() throws IOException {
        caseManager = AlbertaCovid19CaseManager.getInstance();
    }

    @Test
    void getAlbertaCovid19CaseList()
    {
        assertEquals(466616, caseManager.getAlbertaCovid19CaseList().size());
    }

    @Test
    void activeCases()
    {
        assertEquals(61_615, caseManager.countTotalActiveCases());
    }

    @Test
    void activeCasesByZone()
    {
        assertEquals(29_867, caseManager.countActiveCasesByAhsZone("Calgary Zone"));
        assertEquals(21_317, caseManager.countActiveCasesByAhsZone("Edmonton Zone"));
    }

    @Test
    void distinctAhsZones()
    {
        List<String> ahsZoneList = caseManager.distinctAhsZones();
        ahsZoneList.forEach(System.out::println);
        assertEquals(6, ahsZoneList.size());
    }
}
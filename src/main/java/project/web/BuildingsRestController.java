
package project.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.service.BuildingService;

@RestController
@RequestMapping("/building")
public class BuildingsRestController {

    private final BuildingService buildingService;

    @Autowired
    public BuildingsRestController(BuildingService buildingService) {
        this.buildingService = buildingService;
    }

//    @RequestMapping(value = "/findAllCities")
//    public @ResponseBody String findCities() {
//        List<City> all = this.cityRepository.findAll();
//        StringBuilder sb = new StringBuilder();
//        for (City city : all) {
//            sb.append(city).append(System.lineSeparator());
//        }
//        return sb.toString();
//    }
    //     http://localhost:8080/findAllCities
//    @GetMapping(value = "/findBuildingId/{id}")
//    public @ResponseBody String findBuildingById(@RequestParam("id") Long id, Model model) {
//        BuildingEntity currentBuilding = this.buildingService.findBuildingById(id);
//        model.addAttribute("currentBuilding", currentBuilding);
//        return "current-building";
//    }


    //    http://localhost:8080/findBuildingId?id=1

//    @RequestMapping(value = "/findCities",params = {"name"})
//    public @ResponseBody String findAllCitiesByName(@RequestParam(value = "name") String name) {
//        Optional<City> byName = this.cityRepository.findByName(name.trim());
//        if (byName.isPresent()) {
//            return byName.get().toString();
//        }
//        return "City with this id is not present";
//    }

    //    http://localhost:8080/findCities?name= Plovdiv
}
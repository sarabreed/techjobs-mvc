package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", ListController.columnChoices);
        return "search";
    }

    // TODO #1 - Create handler to process search request and display results

    @RequestMapping(value = "results")
    public String search(@RequestParam String searchType, @RequestParam String searchTerm, Model model) {
        Iterable<HashMap<String, String>> jobs;
        if (searchType.equals("all")) {
            jobs = JobData.findByValue(searchTerm);
        }
        else {
            jobs = JobData.findByColumnAndValue(searchType, searchTerm);
            model.addAttribute("selectedColumn", searchType);
        }

        model.addAttribute("columns", ListController.columnChoices);
        model.addAttribute("jobs", jobs);

        return "search";
    }


//    @RequestMapping(value = "search")
//    public String searchResults(Model model, @RequestParam String column, @RequestParam String value) {
//        if (column.equals("all")) {
//            ArrayList<HashMap<String, String>> jobs = JobData.findByValue(value);
//            model.addAttribute("title", "All Jobs");
//            model.addAttribute("jobs", jobs);
//            return "search-all";
//        } else {
//            ArrayList<HashMap<String, String>> jobs = JobData.findByColumnAndValue(column, value);
//            model.addAttribute("title", "All " + ListController.columnChoices.get(column) + " Values");
//            model.addAttribute("column", column);
//            model.addAttribute("jobs", jobs);
//            return "search-column";
//        }
//    }


}

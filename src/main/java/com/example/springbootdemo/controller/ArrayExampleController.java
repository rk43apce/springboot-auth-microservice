package com.example.springbootdemo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
// import java.util.ArrayList;
// import java.util.Arrays;
// import java.util.LinkedHashMap;
// import java.util.List;
// import java.util.Map;

import java.util.*;

@RestController
@RequestMapping("/api/v1/arrays")
public class ArrayExampleController {

    private static final Logger logger = LoggerFactory.getLogger(ArrayExampleController.class);

    private static final int[] SAMPLE_NUMBERS = {42, 7, 19, 7, 88, 3};

    @GetMapping
    public Map<String, Object> overview() {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("topic", "Java Array Deep Dive");
        response.put("definition", "An array stores a fixed number of values of the same type in indexed positions.");
        response.put("sampleArray", SAMPLE_NUMBERS);
        response.put("importantRules", List.of(
                "Array index starts at 0.",
                "Array length is fixed after creation.",
                "Primitive arrays like int[] store primitive values.",
                "Object arrays like String[] store references to objects.",
                "Access by index is fast because Java can calculate the memory position directly."
        ));
        response.put("tryEndpoints", List.of(
                "/api/v1/arrays/access?index=2",
                "/api/v1/arrays/traverse",
                "/api/v1/arrays/update?index=1&value=99",
                "/api/v1/arrays/search?target=7",
                "/api/v1/arrays/sort",
                "/api/v1/arrays/copy",
                "/api/v1/arrays/matrix",
                "/api/v1/arrays/reverse"
        ));
        
        return response;
    }

    @GetMapping("/access")
    public Object accessByIndex(@RequestParam int index)
    {


        Map<String, Object> response = new LinkedHashMap<>();

        
        response.put("response", Map.of(

            "status", "This endpoint is under construction. Please check back later for a detailed demonstration of array access by index.",
            "requestedIndex", index,
            "suggestion", "In the meantime, you can explore the overview endpoint for general array concepts.",
            "tryEndpoints", Map.of(
                "overview", "/api/v1/arrays",
                "traverse", "/api/v1/arrays/traverse",
                "update", "/api/v1/arrays/update?index=1&value=99",
                "search", "/api/v1/arrays/search?target=7",
                "sort", "/api/v1/arrays/sort",
                "copy", "/api/v1/arrays/copy",
                "matrix", "/api/v1/arrays/matrix",
                "reverse", "/api/v1/arrays/reverse1"
            )
        ));

        return response;
    


     
        //  Map<String, Object> response = new LinkedHashMap<>();

        //     response.put("array", SAMPLE_NUMBERS);
        //     response.put("requestedIndex", index);
        //     response.put("valueAtIndex", 19);
        //     response.put("explanation", "This is dummy response data for testing.");

        //     return ResponseEntity.ok(response);
        


        // if (index < 0 || index >= SAMPLE_NUMBERS.length) {
        //     return ResponseEntity.badRequest().body(message(
        //             "Invalid index",
        //             "Use an index between 0 and " + (SAMPLE_NUMBERS.length - 1)
        //     ));
        // }

        // Map<String, Object> response = new LinkedHashMap<>();
        // response.put("array", SAMPLE_NUMBERS);
        // response.put("requestedIndex", index);
        // response.put("valueAtIndex", SAMPLE_NUMBERS[index]);
        // response.put("explanation", "numbers[" + index + "] reads the value stored at index " + index + ".");
        // return ResponseEntity.ok(response);

        //    int[] dummyArray = {10, 20, 30, 40, 50};

        // return ResponseEntity.ok(dummyArray);

    }

    @GetMapping(value = "/posts", produces = MediaType.APPLICATION_JSON_VALUE)


    public String getPosts()  {

        try {
                HttpClient client = HttpClient.newHttpClient();

                HttpRequest request = HttpRequest.newBuilder()

                
                .uri(URI.create("https://jsonplaceholder.typicode.com/posts"))
                .GET()
                .build();

                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

                return response.body();

        
                

        } catch (Exception e) {
            return "HttpClient class not found. Please ensure you are using Java 11 or higher.";
        }
        
       
    }

    @GetMapping(value = "/posts-rest-client", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getPostsUsingRestClient() {
        RestClient restClient = RestClient.create();
        

        String response = restClient.get()
                .uri("https://jsonplaceholder.typicode.com/posts")
                .retrieve()
                .body(String.class);

        logger.info("RestClient API response: {}", response);

        return response;
    }

    @GetMapping(value = "/posts-rest-client-2", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getPostsUsingRestClient2() {
        

        RestClient restClient = RestClient.create();

        String response = restClient.get()
                .uri("https://jsonplaceholder.typicode.com/posts")
                .retrieve()
                .body(String.class);    

        logger.info("RestClient API response: {}", response);
        
        return response;

    }
    

    @GetMapping("/traverse")
    public Map<String, Object> traverse() {
        List<Map<String, Object>> steps = new ArrayList<>();

        for (int index = 0; index < SAMPLE_NUMBERS.length; index++) {
            Map<String, Object> step = new LinkedHashMap<>();
            step.put("index", index);
            step.put("value", SAMPLE_NUMBERS[index]);
            step.put("expression", "numbers[" + index + "]");
            steps.add(step);
        }

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("array", SAMPLE_NUMBERS);
        response.put("length", SAMPLE_NUMBERS.length);
        response.put("loopUsed", "for (int index = 0; index < numbers.length; index++)");
        response.put("steps", steps);
        return response;
    }

    @GetMapping("/update")
    public ResponseEntity<Map<String, Object>> updateCopy(@RequestParam int index, @RequestParam int value) {
        if (index < 0 || index >= SAMPLE_NUMBERS.length) {
            return ResponseEntity.badRequest().body(message(
                    "Invalid index",
                    "Use an index between 0 and " + (SAMPLE_NUMBERS.length - 1)
            ));
        }

        int[] before = Arrays.copyOf(SAMPLE_NUMBERS, SAMPLE_NUMBERS.length);
        int[] after = Arrays.copyOf(SAMPLE_NUMBERS, SAMPLE_NUMBERS.length);
        after[index] = value;

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("before", before);
        response.put("operation", "numbers[" + index + "] = " + value);
        response.put("after", after);
        response.put("note", "This endpoint updates a copy so the demo sample remains unchanged for other requests.");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search")
    public Map<String, Object> search(@RequestParam int target) {
        List<Integer> matchingIndexes = new ArrayList<>();

        for (int index = 0; index < SAMPLE_NUMBERS.length; index++) {
            if (SAMPLE_NUMBERS[index] == target) {
                matchingIndexes.add(index);
            }
        }

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("array", SAMPLE_NUMBERS);
        response.put("target", target);
        response.put("found", !matchingIndexes.isEmpty());
        response.put("matchingIndexes", matchingIndexes);
        response.put("explanation", "Linear search checks one element at a time until all values are tested.");
        return response;
    }

    @GetMapping("/sort")
    public Map<String, Object> sortCopy() {
        int[] sorted = Arrays.copyOf(SAMPLE_NUMBERS, SAMPLE_NUMBERS.length);
        Arrays.sort(sorted);

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("before", SAMPLE_NUMBERS);
        response.put("afterSort", sorted);
        response.put("methodUsed", "Arrays.sort(numbers)");
        response.put("note", "Sorting changes the array order, so this endpoint sorts a copy.");
        return response;
    }

    @GetMapping("/copy")
    public Map<String, Object> copyExample() {
        int[] original = Arrays.copyOf(SAMPLE_NUMBERS, SAMPLE_NUMBERS.length);
        int[] directReference = original;
        int[] independentCopy = Arrays.copyOf(original, original.length);

        directReference[0] = 500;
        independentCopy[1] = 600;

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("sampleBeforeChanges", SAMPLE_NUMBERS);
        response.put("originalAfterDirectReferenceChange", original);
        response.put("directReferenceAfterChange", directReference);
        response.put("independentCopyAfterChange", independentCopy);
        response.put("lesson", "A direct assignment points to the same array. Arrays.copyOf creates a separate array.");
        return response;
    }

    @GetMapping("/matrix")
    public Map<String, Object> matrixExample() {
        int[][] marks = {
                {85, 90, 78},
                {76, 88, 91},
                {92, 81, 89}
        };

        List<Map<String, Object>> cells = new ArrayList<>();
        for (int row = 0; row < marks.length; row++) {
            for (int column = 0; column < marks[row].length; column++) {
                Map<String, Object> cell = new LinkedHashMap<>();
                cell.put("row", row);
                cell.put("column", column);
                cell.put("value", marks[row][column]);
                cell.put("expression", "marks[" + row + "][" + column + "]");
                cells.add(cell);
            }
        }

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("matrix", marks);
        response.put("rows", marks.length);
        response.put("columnsInFirstRow", marks[0].length);
        response.put("cells", cells);
        response.put("lesson", "A 2D array is an array whose elements are also arrays.");
        return response;
    }

    @GetMapping("/reverse")
    public Map<String, Object> reverseCopy() {
        int[] reversed = Arrays.copyOf(SAMPLE_NUMBERS, SAMPLE_NUMBERS.length);

        int left = 0;
        int right = reversed.length - 1;
        List<String> swaps = new ArrayList<>();

        while (left < right) {
            swaps.add("swap index " + left + " with index " + right);
            int temp = reversed[left];
            reversed[left] = reversed[right];
            reversed[right] = temp;
            left++;
            right--;
        }

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("before", SAMPLE_NUMBERS);
        response.put("afterReverse", reversed);
        response.put("swaps", swaps);
        response.put("lesson", "Two-pointer reversal swaps values from both ends until the pointers meet.");
        return response;
    }

    private Map<String, Object> message(String error, String suggestion) {
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("error", error);
        response.put("suggestion", suggestion);
        return response;
    }
}

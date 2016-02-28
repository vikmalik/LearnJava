package com.learnjava.general;

import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author vikmalik
 */
public class GenerateJSONFromTemplate {
    private static final String TEAM_FILTER_JSON = "[{\"fieldConditions\":[],\"paramConnector\":\"AND\",\"fieldName\":\"teamname\"}]";
    private static final String TEAM_FIELD_JSON = "{\"fieldOperator\":\"equals\",\"fieldConnector\":\"OR\",\"fieldValues\":[\"%s\"]}";
    
    

    public static void main(String[] args) {
        List<String> teamList = new ArrayList<>(2);
        teamList.add("MS");
        //teamList.add("CUCI");
        
        JSONArray myjson = new JSONArray();
        
        GenerateJSONFromTemplate instance = new GenerateJSONFromTemplate();
        JSONArray responseJSON = instance.getJSON(teamList, myjson);
        
        System.out.println("responseJSON = " + responseJSON);
        

    }
    
    private JSONArray getJSON(List<String> teamNameList, JSONArray paramsArray) throws JSONException {
        System.out.println("Inside getJSON(), Processing of Json array for Finesse user");
        JSONArray baseJSON = null;
        int numberOfTeams = teamNameList.size();
        if(numberOfTeams > 0){
            baseJSON = new JSONArray(TEAM_FILTER_JSON);
            JSONObject teamFieldConditionJSON;
            String teamNameFilter;

            JSONArray teamsFieldConditionJSONArray = baseJSON.getJSONObject(0).optJSONArray("fieldConditions");

            for (int i = 0; i < numberOfTeams ; i++) {
                teamNameFilter = String.format(TEAM_FIELD_JSON, teamNameList.get(i));
                teamFieldConditionJSON = new JSONObject(teamNameFilter);
                teamsFieldConditionJSONArray.put(teamFieldConditionJSON);
            }

            if(numberOfTeams > 1 ){
                teamsFieldConditionJSONArray.getJSONObject(numberOfTeams-1).remove("fieldConnector");
            }
        }
        
        return baseJSON;
    }

    private JSONArray addingFilter(List<String> teamNameList, JSONArray paramsArray) throws JSONException {
        System.out.println("Inside addingFilter(), Processing of Json array for Finesse user");

        String initial_mandate_String = "[{\"paramConnector\":\"AND\",\"fieldName\":\"teamname\",\"fieldConditions\":[";
        String initial_optional_String = "{\"fieldConnector\":\"OR\",\"fieldOperator\":\"equals\",\"fieldValues\"";
        String last_String = ",{\"fieldOperator\":\"equals\",\"fieldValues\"";
        String end_String = "]}]";

    	// TeamFilter below is nothing but alias for Json array to be appended at the beginning of already received paramsArray,
        // so as to achieve added restriction based on teams of the Finesse user on the search results.
        String TeamFilter = initial_mandate_String + initial_optional_String;
        // counter below will track the number of teams assigned to current Finesse user
        for (int counter = 0; counter < teamNameList.size(); counter++) {
            if (counter == 0) {
                TeamFilter = String.format(TeamFilter + ":[\"%s\"]}", teamNameList.get(counter));
            } else {
                if (teamNameList.size() - 1 > counter) {
                    TeamFilter = TeamFilter + ',' + initial_optional_String;
                    TeamFilter = String.format(TeamFilter + ":[\"%s\"]}", teamNameList.get(counter));
                } else {
                    TeamFilter = TeamFilter + last_String;
                    TeamFilter = String.format(TeamFilter + ":[\"%s\"]}", teamNameList.get(counter));
                }
            }
        }

        TeamFilter = TeamFilter + end_String;
        System.out.println("Inside addingFilter(), JsonArray With team names obtained :: " + TeamFilter);
        // now that a TeamFilter is obtained, append the original JsonArray to this and return to the calling method
        JSONArray paramArrayWithTeamFilter = new JSONArray(TeamFilter);
        for (int i = 0; i < paramsArray.length(); i++) {
            paramArrayWithTeamFilter.put(paramsArray.get(i));
        }
        return paramArrayWithTeamFilter;
    }
}

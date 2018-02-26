

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

/**
 * Servlet implementation class MovieSuggestion
 */
@WebServlet("/hero-suggestion")
public class HeroSuggestion extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public static List<String> marvelHeros = Arrays.asList(
			"Blade",
			"Ghost Rider",
			"Luke Cage",
			"Silver Surfer",
			"Beast",
			"Thing",
			"Black Panther",
			"Invisible Woman",
			"Nick Fury",
			"Storm",
			"Iron Man",
			"Professor X",
			"Hulk",
			"Cyclops",
			"Thor",
			"Jean Grey",
			"Wolverine",
			"Daredevil",
			"Captain America",
			"Spider-Man");
	
	public static List<String> dcHeros = Arrays.asList(
			"Superman",
			"Batman",
			"Wonder Woman",
			"Flash",
			"Green Lantern",
			"Catwoman",
			"Nightwing",
			"Captain Marvel",
			"Aquaman",
			"Green Arrow",
			"Martian Manhunter",
			"Batgirl",
			"Supergirl",
			"Black Canary",
			"Hawkgirl",
			"Cyborg",
			"Robin");
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HeroSuggestion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			JsonArray jsonArray = new JsonArray();
			
			String query = request.getParameter("query");
			
			if (query == null || query.trim().isEmpty()) {
				response.getWriter().write(jsonArray.toString());
				return;
			}	
						
			List<String> matchedMarvelHeros = new ArrayList<>();
			for (String s : marvelHeros) {
				if (s.toLowerCase().contains(query.toLowerCase())) {
					matchedMarvelHeros.add(s);
				}
			}
			
			List<String> matchedDcHeros = new ArrayList<>();
			for (String s : dcHeros) {
				if (s.toLowerCase().contains(query.toLowerCase())) {
					matchedDcHeros.add(s);
				}
			}
			
			for (String s : matchedMarvelHeros) {
				jsonArray.add(generateJsonObject(s, "marvel"));
			}
			for (String s: matchedDcHeros) {
				jsonArray.add(generateJsonObject(s, "dc"));
			}
			
			response.getWriter().write(jsonArray.toString());
			return;
		} catch (Exception e) {
			System.out.println(e);
			response.sendError(500, e.getMessage());
		}
	}
	
	private static JsonObject generateJsonObject(String hero, String category) {
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("value", hero);
		JsonObject categoryJsonObject = new JsonObject();
		categoryJsonObject.addProperty("category", category);
		jsonObject.add("data", categoryJsonObject);
		return jsonObject;
	}


}

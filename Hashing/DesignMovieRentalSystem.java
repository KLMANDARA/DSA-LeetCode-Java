import java.util.*;

class MovieRentingSystem {
    // Map movieId -> available copies (price, shop)
    private Map<Integer, TreeSet<int[]>> availableByMovie;
    // Global set of rented movies (price, shop, movie)
    private TreeSet<int[]> rented;
    // Map (shop,movie) -> price
    private Map<String, Integer> priceMap;

    public MovieRentingSystem(int n, int[][] entries) {
        availableByMovie = new HashMap<>();
        priceMap = new HashMap<>();

        // Comparator for available movies per movieId
        Comparator<int[]> cmpAvail = (a, b) -> {
            if (a[0] != b[0]) return a[0] - b[0];   // by price
            return a[1] - b[1];                     // then by shop
        };

        // Comparator for rented movies
        Comparator<int[]> cmpRented = (a, b) -> {
            if (a[0] != b[0]) return a[0] - b[0];   // by price
            if (a[1] != b[1]) return a[1] - b[1];   // then by shop
            return a[2] - b[2];                     // then by movie
        };

        rented = new TreeSet<>(cmpRented);

        // Initialize data
        for (int[] e : entries) {
            int shop = e[0], movie = e[1], price = e[2];
            availableByMovie.putIfAbsent(movie, new TreeSet<>(cmpAvail));
            availableByMovie.get(movie).add(new int[]{price, shop});
            priceMap.put(shop + "#" + movie, price);
        }
    }

    public List<Integer> search(int movie) {
        List<Integer> res = new ArrayList<>();
        if (!availableByMovie.containsKey(movie)) return res;

        int count = 0;
        for (int[] p : availableByMovie.get(movie)) {
            res.add(p[1]); // shop
            if (++count == 5) break;
        }
        return res;
    }

    public void rent(int shop, int movie) {
        int price = priceMap.get(shop + "#" + movie);
        availableByMovie.get(movie).remove(new int[]{price, shop});
        rented.add(new int[]{price, shop, movie});
    }

    public void drop(int shop, int movie) {
        int price = priceMap.get(shop + "#" + movie);
        rented.remove(new int[]{price, shop, movie});
        availableByMovie.get(movie).add(new int[]{price, shop});
    }

    public List<List<Integer>> report() {
        List<List<Integer>> res = new ArrayList<>();
        int count = 0;
        for (int[] p : rented) {
            res.add(Arrays.asList(p[1], p[2])); // [shop, movie]
            if (++count == 5) break;
        }
        return res;
    }
}

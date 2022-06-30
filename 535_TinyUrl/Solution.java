public class Codec {

    private static String header = "http://tinyurl.com/";
    private Map<Integer, String> map = new HashMap<>();
    private int id = 0;

    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        id++;
        map.put(id, longUrl);

        return header + id;
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        int index = shortUrl.lastIndexOf('/');
        int id = Integer.valueOf(shortUrl.substring(index+1));

        return map.get(id);
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(url));

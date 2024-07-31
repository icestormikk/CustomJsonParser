public class Main {
    public static void main(String[] args) {
        JsonParser parser = new JsonParser("test_json_file.json");
        System.out.println(parser.parse());
    }
}

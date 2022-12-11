package pl.put.poznan.JSON_tools;

/**
 * Test helper class
 */
public class JsonToolsTestHelper
{
    public static String CORRECT_JSON_EXAMPLE = "{\n" + "  \"widget\" : {\n" + "    \"debug\" : \"on\",\n"
        + "    \"window\" : {\n" + "      \"title\" : \"Sample Konfabulator Widget\",\n"
        + "      \"name\" : \"main_window\",\n" + "      \"width\" : 500,\n" + "      \"height\" : 500\n"
        + "    },\n" + "    \"image\" : {\n" + "      \"src\" : \"Images/Sun.png\",\n"
        + "      \"name\" : \"sun1\",\n" + "      \"hOffset\" : 250,\n" + "      \"vOffset\" : 250,\n"
        + "      \"alignment\" : \"center\"\n" + "    },\n" + "    \"text\" : {\n"
        + "      \"data\" : \"Click Here\",\n" + "      \"size\" : 36,\n" + "      \"style\" : \"bold\",\n"
        + "      \"name\" : \"text1\",\n" + "      \"hOffset\" : 250,\n" + "      \"vOffset\" : 100,\n"
        + "      \"alignment\" : \"center\",\n"
        + "      \"onMouseUp\" : \"sun1.opacity = (sun1.opacity / 100) * 90;\"\n" + "    }\n" + "  }\n" + "}";

    public static String CORRECT_MINIMIZED_JSON_EXAMPLE =
        "{\"widget\":{\"debug\":\"on\",\"window\":{\"title\":\"Sample Konfabulator Widget\",\"name\":\"main_window\",\"width\":500,\"height\":500},\"image\":{\"src\":\"Images/Sun.png\",\"name\":\"sun1\",\"hOffset\":250,\"vOffset\":250,\"alignment\":\"center\"},\"text\":{\"data\":\"Click Here\",\"size\":36,\"style\":\"bold\",\"name\":\"text1\",\"hOffset\":250,\"vOffset\":100,\"alignment\":\"center\",\"onMouseUp\":\"sun1.opacity \\u003d (sun1.opacity / 100) * 90;\"}}}";
    public static String WRONG_JSON_FORMAT_EXAMPLE =
        "{widget\":{\"debug\":\"on\",\"window\":{\"title\":\"Sample Konfabulator Widget\",\"name\":\"main_window";
}

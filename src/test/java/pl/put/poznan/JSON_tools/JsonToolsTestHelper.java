package pl.put.poznan.JSON_tools;

/**
 * Test helper class
 */
public class JsonToolsTestHelper
{
    public static String CORRECT_JSON_EXAMPLE =
            "{\"widget\": {\n" +
                    "  \"image\": {\n" +
                    "    \"vOffset\": 250,\n" +
                    "    \"src\": \"Images/Sun.png\",\n" +
                    "    \"name\": \"sun1\",\n" +
                    "    \"alignment\": \"center\",\n" +
                    "    \"hOffset\": 250\n" +
                    "  },\n" +
                    "  \"debug\": \"on\",\n" +
                    "  \"window\": {\n" +
                    "    \"name\": \"main_window\",\n" +
                    "    \"width\": 500,\n" +
                    "    \"title\": \"Sample Konfabulator Widget\",\n" +
                    "    \"height\": 500\n" +
                    "  },\n" +
                    "  \"text\": {\n" +
                    "    \"vOffset\": 100,\n" +
                    "    \"data\": \"Click Here\",\n" +
                    "    \"size\": 36,\n" +
                    "    \"name\": \"text1\",\n" +
                    "    \"style\": \"bold\",\n" +
                    "    \"alignment\": \"center\",\n" +
                    "    \"onMouseUp\": \"sun1.opacity = (sun1.opacity / 100) * 90;\",\n" +
                    "    \"hOffset\": 250\n" +
                    "  }\n" +
                    "}}";
    public static String CORRECT_MINIMIZED_JSON_EXAMPLE =
            "{\"widget\":{\"image\":{\"vOffset\":250,\"src\":\"Images/Sun.png\",\"name\":\"sun1\",\"alignment\":\"center\",\"hOffset\":250},\"debug\":\"on\",\"window\":{\"name\":\"main_window\",\"width\":500,\"title\":\"Sample Konfabulator Widget\",\"height\":500},\"text\":{\"vOffset\":100,\"data\":\"Click Here\",\"size\":36,\"name\":\"text1\",\"style\":\"bold\",\"alignment\":\"center\",\"onMouseUp\":\"sun1.opacity = (sun1.opacity / 100) * 90;\",\"hOffset\":250}}}";
    public static String WRONG_JSON_FORMAT_EXAMPLE =
            "{widget\":{\"debug\":\"on\",\"window\":{\"title\":\"Sample Konfabulator Widget\",\"name\":\"main_window";
}

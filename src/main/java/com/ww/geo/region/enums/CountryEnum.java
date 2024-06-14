package com.ww.geo.region.enums;


import com.ww.geo.region.util.Lists;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum CountryEnum {
    AD("安道尔", "Andorra", "Andorra", "EU", "Andorra la Vella", Lists.newArrayList("376"), Lists.newArrayList("EUR"), Lists.newArrayList("ca")),
    AE("阿联酋", "United Arab Emirates", "دولة الإمارات العربية المتحدة", "AS", "Abu Dhabi", Lists.newArrayList("971"), Lists.newArrayList("AED"), Lists.newArrayList("ar")),
    AF("阿富汗", "Afghanistan", "افغانستان", "AS", "Kabul", Lists.newArrayList("93"), Lists.newArrayList("AFN"), Lists.newArrayList("ps", "uz", "tk")),
    AG("安提瓜和巴布达", "Antigua and Barbuda", "Antigua and Barbuda", "NA", "Saint John's", Lists.newArrayList("1268"), Lists.newArrayList("XCD"), Lists.newArrayList("en")),
    AI("安圭拉", "Anguilla", "Anguilla", "NA", "The Valley", Lists.newArrayList("1264"), Lists.newArrayList("XCD"), Lists.newArrayList("en")),
    AL("阿尔巴尼亚", "Albania", "Shqipëria", "EU", "Tirana", Lists.newArrayList("355"), Lists.newArrayList("ALL"), Lists.newArrayList("sq")),
    AM("亚美尼亚", "Armenia", "Հայաստան", "AS", "Yerevan", Lists.newArrayList("374"), Lists.newArrayList("AMD"), Lists.newArrayList("hy", "ru")),
    AO("安哥拉", "Angola", "Angola", "AF", "Luanda", Lists.newArrayList("244"), Lists.newArrayList("AOA"), Lists.newArrayList("pt")),
    AQ("南极洲", "Antarctica", "Antarctica", "AN", "", Lists.newArrayList("672"), Lists.newArrayList(), Lists.newArrayList()),
    AR("阿根廷", "Argentina", "Argentina", "SA", "Buenos Aires", Lists.newArrayList("54"), Lists.newArrayList("ARS"), Lists.newArrayList("es", "gn")),
    AS("美属萨摩亚", "American Samoa", "American Samoa", "OC", "Pago Pago", Lists.newArrayList("1684"), Lists.newArrayList("USD"), Lists.newArrayList("en", "sm")),
    AT("奥地利", "Austria", "Österreich", "EU", "Vienna", Lists.newArrayList("43"), Lists.newArrayList("EUR"), Lists.newArrayList("de")),
    AU("澳大利亚", "Australia", "Australia", "OC", "Canberra", Lists.newArrayList("61"), Lists.newArrayList("AUD"), Lists.newArrayList("en")),
    AW("阿鲁巴", "Aruba", "Aruba", "NA", "Oranjestad", Lists.newArrayList("297"), Lists.newArrayList("AWG"), Lists.newArrayList("nl", "pa")),
    AX("奥兰", "Åland", "Åland", "EU", "Mariehamn", Lists.newArrayList("358"), Lists.newArrayList("EUR"), Lists.newArrayList("sv")),
    AZ("阿塞拜疆", "Azerbaijan", "Azərbaycan", "AS", "Baku", Lists.newArrayList("994"), Lists.newArrayList("AZN"), Lists.newArrayList("az")),
    BA("波黑", "Bosnia and Herzegovina", "Bosna i Hercegovina", "EU", "Sarajevo", Lists.newArrayList("387"), Lists.newArrayList("BAM"), Lists.newArrayList("bs", "hr", "sr")),
    BB("巴巴多斯", "Barbados", "Barbados", "NA", "Bridgetown", Lists.newArrayList("1246"), Lists.newArrayList("BBD"), Lists.newArrayList("en")),
    BD("孟加拉国", "Bangladesh", "Bangladesh", "AS", "Dhaka", Lists.newArrayList("880"), Lists.newArrayList("BDT"), Lists.newArrayList("bn")),
    BE("比利时", "Belgium", "België", "EU", "Brussels", Lists.newArrayList("32"), Lists.newArrayList("EUR"), Lists.newArrayList("nl", "fr", "de")),
    BF("布基纳法索", "Burkina Faso", "Burkina Faso", "AF", "Ouagadougou", Lists.newArrayList("226"), Lists.newArrayList("XOF"), Lists.newArrayList("fr", "ff")),
    BG("保加利亚", "Bulgaria", "България", "EU", "Sofia", Lists.newArrayList("359"), Lists.newArrayList("BGN"), Lists.newArrayList("bg")),
    BH("巴林", "Bahrain", "‏البحرين", "AS", "Manama", Lists.newArrayList("973"), Lists.newArrayList("BHD"), Lists.newArrayList("ar")),
    BI("布隆迪", "Burundi", "Burundi", "AF", "Bujumbura", Lists.newArrayList("257"), Lists.newArrayList("BIF"), Lists.newArrayList("fr", "rn")),
    BJ("贝宁", "Benin", "Bénin", "AF", "Porto-Novo", Lists.newArrayList("229"), Lists.newArrayList("XOF"), Lists.newArrayList("fr")),
    BL("圣巴泰勒米", "Saint Barthélemy", "Saint-Barthélemy", "NA", "Gustavia", Lists.newArrayList("590"), Lists.newArrayList("EUR"), Lists.newArrayList("fr")),
    BM("百慕大", "Bermuda", "Bermuda", "NA", "Hamilton", Lists.newArrayList("1441"), Lists.newArrayList("BMD"), Lists.newArrayList("en")),
    BN("文莱", "Brunei", "Negara Brunei Darussalam", "AS", "Bandar Seri Begawan", Lists.newArrayList("673"), Lists.newArrayList("BND"), Lists.newArrayList("ms")),
    BO("玻利维亚", "Bolivia", "Bolivia", "SA", "Sucre", Lists.newArrayList("591"), Lists.newArrayList("BOB", "BOV"), Lists.newArrayList("es", "ay", "qu")),
    BQ("荷兰加勒比区", "Bonaire", "Bonaire", "NA", "Kralendijk", Lists.newArrayList("5997"), Lists.newArrayList("USD"), Lists.newArrayList("nl")),
    BR("巴西", "Brazil", "Brasil", "SA", "Brasília", Lists.newArrayList("55"), Lists.newArrayList("BRL"), Lists.newArrayList("pt")),
    BS("巴哈马", "Bahamas", "Bahamas", "NA", "Nassau", Lists.newArrayList("1242"), Lists.newArrayList("BSD"), Lists.newArrayList("en")),
    BT("不丹", "Bhutan", "ʼbrug-yul", "AS", "Thimphu", Lists.newArrayList("975"), Lists.newArrayList("BTN", "INR"), Lists.newArrayList("dz")),
    BV("布韦岛", "Bouvet Island", "Bouvetøya", "AN", "", Lists.newArrayList("47"), Lists.newArrayList("NOK"), Lists.newArrayList("no", "nb", "nn")),
    BW("博茨瓦纳", "Botswana", "Botswana", "AF", "Gaborone", Lists.newArrayList("267"), Lists.newArrayList("BWP"), Lists.newArrayList("en", "tn")),
    BY("白俄罗斯", "Belarus", "Белару́сь", "EU", "Minsk", Lists.newArrayList("375"), Lists.newArrayList("BYN"), Lists.newArrayList("be", "ru")),
    BZ("伯利兹", "Belize", "Belize", "NA", "Belmopan", Lists.newArrayList("501"), Lists.newArrayList("BZD"), Lists.newArrayList("en", "es")),
    CA("加拿大", "Canada", "Canada", "NA", "Ottawa", Lists.newArrayList("1"), Lists.newArrayList("CAD"), Lists.newArrayList("en", "fr")),
    CC("科科斯（基林）群岛", "Cocos [Keeling] Islands", "Cocos (Keeling) Islands", "AS", "West Island", Lists.newArrayList("61"), Lists.newArrayList("AUD"), Lists.newArrayList("en")),
    CD("刚果民主共和国", "Democratic Republic of the Congo", "République démocratique du Congo", "AF", "Kinshasa", Lists.newArrayList("243"), Lists.newArrayList("CDF"), Lists.newArrayList("fr", "ln", "kg", "sw", "lu")),
    CF("中非", "Central African Republic", "Ködörösêse tî Bêafrîka", "AF", "Bangui", Lists.newArrayList("236"), Lists.newArrayList("XAF"), Lists.newArrayList("fr", "sg")),
    CG("刚果共和国", "Republic of the Congo", "République du Congo", "AF", "Brazzaville", Lists.newArrayList("242"), Lists.newArrayList("XAF"), Lists.newArrayList("fr", "ln")),
    CH("瑞士", "Switzerland", "Schweiz", "EU", "Bern", Lists.newArrayList("41"), Lists.newArrayList("CHE", "CHF", "CHW"), Lists.newArrayList("de", "fr", "it")),
    CI("科特迪瓦", "Ivory Coast", "Côte d'Ivoire", "AF", "Yamoussoukro", Lists.newArrayList("225"), Lists.newArrayList("XOF"), Lists.newArrayList("fr")),
    CK("库克群岛", "Cook Islands", "Cook Islands", "OC", "Avarua", Lists.newArrayList("682"), Lists.newArrayList("NZD"), Lists.newArrayList("en")),
    CL("智利", "Chile", "Chile", "SA", "Santiago", Lists.newArrayList("56"), Lists.newArrayList("CLF", "CLP"), Lists.newArrayList("es")),
    CM("喀麦隆", "Cameroon", "Cameroon", "AF", "Yaoundé", Lists.newArrayList("237"), Lists.newArrayList("XAF"), Lists.newArrayList("en", "fr")),
    CN("中国", "China", "中国", "AS", "Beijing", Lists.newArrayList("86"), Lists.newArrayList("CNY"), Lists.newArrayList("zh")),
    CO("哥伦比亚", "Colombia", "Colombia", "SA", "Bogotá", Lists.newArrayList("57"), Lists.newArrayList("COP"), Lists.newArrayList("es")),
    CR("哥斯达黎加", "Costa Rica", "Costa Rica", "NA", "San José", Lists.newArrayList("506"), Lists.newArrayList("CRC"), Lists.newArrayList("es")),
    CU("古巴", "Cuba", "Cuba", "NA", "Havana", Lists.newArrayList("53"), Lists.newArrayList("CUC", "CUP"), Lists.newArrayList("es")),
    CV("佛得角", "Cape Verde", "Cabo Verde", "AF", "Praia", Lists.newArrayList("238"), Lists.newArrayList("CVE"), Lists.newArrayList("pt")),
    CW("库拉索", "Curacao", "Curaçao", "NA", "Willemstad", Lists.newArrayList("5999"), Lists.newArrayList("ANG"), Lists.newArrayList("nl", "pa", "en")),
    CX("圣诞岛", "Christmas Island", "Christmas Island", "AS", "Flying Fish Cove", Lists.newArrayList("61"), Lists.newArrayList("AUD"), Lists.newArrayList("en")),
    CY("塞浦路斯", "Cyprus", "Κύπρος", "EU", "Nicosia", Lists.newArrayList("357"), Lists.newArrayList("EUR"), Lists.newArrayList("el", "tr", "hy")),
    CZ("捷克", "Czechia", "Česká republika", "EU", "Prague", Lists.newArrayList("420"), Lists.newArrayList("CZK"), Lists.newArrayList("cs")),
    DE("德国", "Germany", "Deutschland", "EU", "Berlin", Lists.newArrayList("49"), Lists.newArrayList("EUR"), Lists.newArrayList("de")),
    DJ("吉布提", "Djibouti", "Djibouti", "AF", "Djibouti", Lists.newArrayList("253"), Lists.newArrayList("DJF"), Lists.newArrayList("fr", "ar")),
    DK("丹麦", "Denmark", "Danmark", "EU", "Copenhagen", Lists.newArrayList("45"), Lists.newArrayList("DKK"), Lists.newArrayList("da")),
    DM("多米尼克", "Dominica", "Dominica", "NA", "Roseau", Lists.newArrayList("1767"), Lists.newArrayList("XCD"), Lists.newArrayList("en")),
    DO("多米尼加", "Dominican Republic", "República Dominicana", "NA", "Santo Domingo", Lists.newArrayList("1809", "1829", "1849"), Lists.newArrayList("DOP"), Lists.newArrayList("es")),
    DZ("阿尔及利亚", "Algeria", "الجزائر", "AF", "Algiers", Lists.newArrayList("213"), Lists.newArrayList("DZD"), Lists.newArrayList("ar")),
    EC("厄瓜多尔", "Ecuador", "Ecuador", "SA", "Quito", Lists.newArrayList("593"), Lists.newArrayList("USD"), Lists.newArrayList("es")),
    EE("爱沙尼亚", "Estonia", "Eesti", "EU", "Tallinn", Lists.newArrayList("372"), Lists.newArrayList("EUR"), Lists.newArrayList("et")),
    EG("埃及", "Egypt", "مصر‎", "AF", "Cairo", Lists.newArrayList("20"), Lists.newArrayList("EGP"), Lists.newArrayList("ar")),
    EH("西撒哈拉", "Western Sahara", "الصحراء الغربية", "AF", "El Aaiún", Lists.newArrayList("212"), Lists.newArrayList("MAD", "DZD", "MRU"), Lists.newArrayList("es")),
    ER("厄立特里亚", "Eritrea", "ኤርትራ", "AF", "Asmara", Lists.newArrayList("291"), Lists.newArrayList("ERN"), Lists.newArrayList("ti", "ar", "en")),
    ES("西班牙", "Spain", "España", "EU", "Madrid", Lists.newArrayList("34"), Lists.newArrayList("EUR"), Lists.newArrayList("es", "eu", "ca", "gl", "oc")),
    ET("埃塞俄比亚", "Ethiopia", "ኢትዮጵያ", "AF", "Addis Ababa", Lists.newArrayList("251"), Lists.newArrayList("ETB"), Lists.newArrayList("am")),
    FI("芬兰", "Finland", "Suomi", "EU", "Helsinki", Lists.newArrayList("358"), Lists.newArrayList("EUR"), Lists.newArrayList("fi", "sv")),
    FJ("斐济", "Fiji", "Fiji", "OC", "Suva", Lists.newArrayList("679"), Lists.newArrayList("FJD"), Lists.newArrayList("en", "fj", "hi", "ur")),
    FK("福克兰群岛", "Falkland Islands", "Falkland Islands", "SA", "Stanley", Lists.newArrayList("500"), Lists.newArrayList("FKP"), Lists.newArrayList("en")),
    FM("密克罗尼西亚联邦", "Micronesia", "Micronesia", "OC", "Palikir", Lists.newArrayList("691"), Lists.newArrayList("USD"), Lists.newArrayList("en")),
    FO("法罗群岛", "Faroe Islands", "Føroyar", "EU", "Tórshavn", Lists.newArrayList("298"), Lists.newArrayList("DKK"), Lists.newArrayList("fo")),
    FR("法国", "France", "France", "EU", "Paris", Lists.newArrayList("33"), Lists.newArrayList("EUR"), Lists.newArrayList("fr")),
    GA("加蓬", "Gabon", "Gabon", "AF", "Libreville", Lists.newArrayList("241"), Lists.newArrayList("XAF"), Lists.newArrayList("fr")),
    GB("英国", "United Kingdom", "United Kingdom", "EU", "London", Lists.newArrayList("44"), Lists.newArrayList("GBP"), Lists.newArrayList("en")),
    GD("格林纳达", "Grenada", "Grenada", "NA", "St. George's", Lists.newArrayList("1473"), Lists.newArrayList("XCD"), Lists.newArrayList("en")),
    GE("格鲁吉亚", "Georgia", "საქართველო", "AS", "Tbilisi", Lists.newArrayList("995"), Lists.newArrayList("GEL"), Lists.newArrayList("ka")),
    GF("法属圭亚那", "French Guiana", "Guyane française", "SA", "Cayenne", Lists.newArrayList("594"), Lists.newArrayList("EUR"), Lists.newArrayList("fr")),
    GG("根西", "Guernsey", "Guernsey", "EU", "St. Peter Port", Lists.newArrayList("44"), Lists.newArrayList("GBP"), Lists.newArrayList("en", "fr")),
    GH("加纳", "Ghana", "Ghana", "AF", "Accra", Lists.newArrayList("233"), Lists.newArrayList("GHS"), Lists.newArrayList("en")),
    GI("直布罗陀", "Gibraltar", "Gibraltar", "EU", "Gibraltar", Lists.newArrayList("350"), Lists.newArrayList("GIP"), Lists.newArrayList("en")),
    GL("格陵兰", "Greenland", "Kalaallit Nunaat", "NA", "Nuuk", Lists.newArrayList("299"), Lists.newArrayList("DKK"), Lists.newArrayList("kl")),
    GM("冈比亚", "Gambia", "Gambia", "AF", "Banjul", Lists.newArrayList("220"), Lists.newArrayList("GMD"), Lists.newArrayList("en")),
    GN("几内亚", "Guinea", "Guinée", "AF", "Conakry", Lists.newArrayList("224"), Lists.newArrayList("GNF"), Lists.newArrayList("fr", "ff")),
    GP("瓜德罗普", "Guadeloupe", "Guadeloupe", "NA", "Basse-Terre", Lists.newArrayList("590"), Lists.newArrayList("EUR"), Lists.newArrayList("fr")),
    GQ("赤道几内亚", "Equatorial Guinea", "Guinea Ecuatorial", "AF", "Malabo", Lists.newArrayList("240"), Lists.newArrayList("XAF"), Lists.newArrayList("es", "fr")),
    GR("希腊", "Greece", "Ελλάδα", "EU", "Athens", Lists.newArrayList("30"), Lists.newArrayList("EUR"), Lists.newArrayList("el")),
    GS("南乔治亚和南桑威奇群岛", "South Georgia and the South Sandwich Islands", "South Georgia", "AN", "King Edward Point", Lists.newArrayList("500"), Lists.newArrayList("GBP"), Lists.newArrayList("en")),
    GT("危地马拉", "Guatemala", "Guatemala", "NA", "Guatemala City", Lists.newArrayList("502"), Lists.newArrayList("GTQ"), Lists.newArrayList("es")),
    GU("关岛", "Guam", "Guam", "OC", "Hagåtña", Lists.newArrayList("1671"), Lists.newArrayList("USD"), Lists.newArrayList("en", "ch", "es")),
    GW("几内亚比绍", "Guinea-Bissau", "Guiné-Bissau", "AF", "Bissau", Lists.newArrayList("245"), Lists.newArrayList("XOF"), Lists.newArrayList("pt")),
    GY("圭亚那", "Guyana", "Guyana", "SA", "Georgetown", Lists.newArrayList("592"), Lists.newArrayList("GYD"), Lists.newArrayList("en")),
    HK("香港", "Hong Kong", "香港", "AS", "City of Victoria", Lists.newArrayList("852"), Lists.newArrayList("HKD"), Lists.newArrayList("zh", "en")),
    HM("赫德岛和麦克唐纳群岛", "Heard Island and McDonald Islands", "Heard Island and McDonald Islands", "AN", "", Lists.newArrayList("61"), Lists.newArrayList("AUD"), Lists.newArrayList("en")),
    HN("洪都拉斯", "Honduras", "Honduras", "NA", "Tegucigalpa", Lists.newArrayList("504"), Lists.newArrayList("HNL"), Lists.newArrayList("es")),
    HR("克罗地亚", "Croatia", "Hrvatska", "EU", "Zagreb", Lists.newArrayList("385"), Lists.newArrayList("HRK"), Lists.newArrayList("hr")),
    HT("海地", "Haiti", "Haïti", "NA", "Port-au-Prince", Lists.newArrayList("509"), Lists.newArrayList("HTG", "USD"), Lists.newArrayList("fr", "ht")),
    HU("匈牙利", "Hungary", "Magyarország", "EU", "Budapest", Lists.newArrayList("36"), Lists.newArrayList("HUF"), Lists.newArrayList("hu")),
    ID("印度尼西亚", "Indonesia", "Indonesia", "AS", "Jakarta", Lists.newArrayList("62"), Lists.newArrayList("IDR"), Lists.newArrayList("id")),
    IE("爱尔兰", "Ireland", "Éire", "EU", "Dublin", Lists.newArrayList("353"), Lists.newArrayList("EUR"), Lists.newArrayList("ga", "en")),
    IL("以色列", "Israel", "יִשְׂרָאֵל", "AS", "Jerusalem", Lists.newArrayList("972"), Lists.newArrayList("ILS"), Lists.newArrayList("he", "ar")),
    IM("马恩岛", "Isle of Man", "Isle of Man", "EU", "Douglas", Lists.newArrayList("44"), Lists.newArrayList("GBP"), Lists.newArrayList("en", "gv")),
    IN("印度", "India", "भारत", "AS", "New Delhi", Lists.newArrayList("91"), Lists.newArrayList("INR"), Lists.newArrayList("hi", "en")),
    IO("英属印度洋领地", "British Indian Ocean Territory", "British Indian Ocean Territory", "AS", "Diego Garcia", Lists.newArrayList("246"), Lists.newArrayList("USD"), Lists.newArrayList("en")),
    IQ("伊拉克", "Iraq", "العراق", "AS", "Baghdad", Lists.newArrayList("964"), Lists.newArrayList("IQD"), Lists.newArrayList("ar", "ku")),
    IR("伊朗", "Iran", "ایران", "AS", "Tehran", Lists.newArrayList("98"), Lists.newArrayList("IRR"), Lists.newArrayList("fa")),
    IS("冰岛", "Iceland", "Ísland", "EU", "Reykjavik", Lists.newArrayList("354"), Lists.newArrayList("ISK"), Lists.newArrayList("is")),
    IT("意大利", "Italy", "Italia", "EU", "Rome", Lists.newArrayList("39"), Lists.newArrayList("EUR"), Lists.newArrayList("it")),
    JE("泽西", "Jersey", "Jersey", "EU", "Saint Helier", Lists.newArrayList("44"), Lists.newArrayList("GBP"), Lists.newArrayList("en", "fr")),
    JM("牙买加", "Jamaica", "Jamaica", "NA", "Kingston", Lists.newArrayList("1876"), Lists.newArrayList("JMD"), Lists.newArrayList("en")),
    JO("约旦", "Jordan", "الأردن", "AS", "Amman", Lists.newArrayList("962"), Lists.newArrayList("JOD"), Lists.newArrayList("ar")),
    JP("日本", "Japan", "日本", "AS", "Tokyo", Lists.newArrayList("81"), Lists.newArrayList("JPY"), Lists.newArrayList("ja")),
    KE("肯尼亚", "Kenya", "Kenya", "AF", "Nairobi", Lists.newArrayList("254"), Lists.newArrayList("KES"), Lists.newArrayList("en", "sw")),
    KG("吉尔吉斯斯坦", "Kyrgyzstan", "Кыргызстан", "AS", "Bishkek", Lists.newArrayList("996"), Lists.newArrayList("KGS"), Lists.newArrayList("ky", "ru")),
    KH("柬埔寨", "Cambodia", "Kâmpŭchéa", "AS", "Phnom Penh", Lists.newArrayList("855"), Lists.newArrayList("KHR"), Lists.newArrayList("km")),
    KI("基里巴斯", "Kiribati", "Kiribati", "OC", "South Tarawa", Lists.newArrayList("686"), Lists.newArrayList("AUD"), Lists.newArrayList("en")),
    KM("科摩罗", "Comoros", "Komori", "AF", "Moroni", Lists.newArrayList("269"), Lists.newArrayList("KMF"), Lists.newArrayList("ar", "fr")),
    KN("圣基茨和尼维斯", "Saint Kitts and Nevis", "Saint Kitts and Nevis", "NA", "Basseterre", Lists.newArrayList("1869"), Lists.newArrayList("XCD"), Lists.newArrayList("en")),
    KP("朝鲜", "North Korea", "북한", "AS", "Pyongyang", Lists.newArrayList("850"), Lists.newArrayList("KPW"), Lists.newArrayList("ko")),
    KR("韩国", "South Korea", "대한민국", "AS", "Seoul", Lists.newArrayList("82"), Lists.newArrayList("KRW"), Lists.newArrayList("ko")),
    KW("科威特", "Kuwait", "الكويت", "AS", "Kuwait City", Lists.newArrayList("965"), Lists.newArrayList("KWD"), Lists.newArrayList("ar")),
    KY("开曼群岛", "Cayman Islands", "Cayman Islands", "NA", "George Town", Lists.newArrayList("1345"), Lists.newArrayList("KYD"), Lists.newArrayList("en")),
    KZ("哈萨克斯坦", "Kazakhstan", "Қазақстан", "AS", "Astana", Lists.newArrayList("76", "77"), Lists.newArrayList("KZT"), Lists.newArrayList("kk", "ru")),
    LA("老挝", "Laos", "ສປປລາວ", "AS", "Vientiane", Lists.newArrayList("856"), Lists.newArrayList("LAK"), Lists.newArrayList("lo")),
    LB("黎巴嫩", "Lebanon", "لبنان", "AS", "Beirut", Lists.newArrayList("961"), Lists.newArrayList("LBP"), Lists.newArrayList("ar", "fr")),
    LC("圣卢西亚", "Saint Lucia", "Saint Lucia", "NA", "Castries", Lists.newArrayList("1758"), Lists.newArrayList("XCD"), Lists.newArrayList("en")),
    LI("列支敦士登", "Liechtenstein", "Liechtenstein", "EU", "Vaduz", Lists.newArrayList("423"), Lists.newArrayList("CHF"), Lists.newArrayList("de")),
    LK("斯里兰卡", "Sri Lanka", "śrī laṃkāva", "AS", "Colombo", Lists.newArrayList("94"), Lists.newArrayList("LKR"), Lists.newArrayList("si", "ta")),
    LR("利比里亚", "Liberia", "Liberia", "AF", "Monrovia", Lists.newArrayList("231"), Lists.newArrayList("LRD"), Lists.newArrayList("en")),
    LS("莱索托", "Lesotho", "Lesotho", "AF", "Maseru", Lists.newArrayList("266"), Lists.newArrayList("LSL", "ZAR"), Lists.newArrayList("en", "st")),
    LT("立陶宛", "Lithuania", "Lietuva", "EU", "Vilnius", Lists.newArrayList("370"), Lists.newArrayList("EUR"), Lists.newArrayList("lt")),
    LU("卢森堡", "Luxembourg", "Luxembourg", "EU", "Luxembourg", Lists.newArrayList("352"), Lists.newArrayList("EUR"), Lists.newArrayList("fr", "de", "lb")),
    LV("拉脱维亚", "Latvia", "Latvija", "EU", "Riga", Lists.newArrayList("371"), Lists.newArrayList("EUR"), Lists.newArrayList("lv")),
    LY("利比亚", "Libya", "‏ليبيا", "AF", "Tripoli", Lists.newArrayList("218"), Lists.newArrayList("LYD"), Lists.newArrayList("ar")),
    MA("摩洛哥", "Morocco", "المغرب", "AF", "Rabat", Lists.newArrayList("212"), Lists.newArrayList("MAD"), Lists.newArrayList("ar")),
    MC("摩纳哥", "Monaco", "Monaco", "EU", "Monaco", Lists.newArrayList("377"), Lists.newArrayList("EUR"), Lists.newArrayList("fr")),
    MD("摩尔多瓦", "Moldova", "Moldova", "EU", "Chișinău", Lists.newArrayList("373"), Lists.newArrayList("MDL"), Lists.newArrayList("ro")),
    ME("黑山", "Montenegro", "Црна Гора", "EU", "Podgorica", Lists.newArrayList("382"), Lists.newArrayList("EUR"), Lists.newArrayList("sr", "bs", "sq", "hr")),
    MF("法属圣马丁", "Saint Martin", "Saint-Martin", "NA", "Marigot", Lists.newArrayList("590"), Lists.newArrayList("EUR"), Lists.newArrayList("en", "fr", "nl")),
    MG("马达加斯加", "Madagascar", "Madagasikara", "AF", "Antananarivo", Lists.newArrayList("261"), Lists.newArrayList("MGA"), Lists.newArrayList("fr", "mg")),
    MH("马绍尔群岛", "Marshall Islands", "M̧ajeļ", "OC", "Majuro", Lists.newArrayList("692"), Lists.newArrayList("USD"), Lists.newArrayList("en", "mh")),
    MK("北马其顿", "North Macedonia", "Северна Македонија", "EU", "Skopje", Lists.newArrayList("389"), Lists.newArrayList("MKD"), Lists.newArrayList("mk")),
    ML("马里", "Mali", "Mali", "AF", "Bamako", Lists.newArrayList("223"), Lists.newArrayList("XOF"), Lists.newArrayList("fr")),
    MM("缅甸", "Myanmar [Burma]", "မြန်မာ", "AS", "Naypyidaw", Lists.newArrayList("95"), Lists.newArrayList("MMK"), Lists.newArrayList("my")),
    MN("蒙古", "Mongolia", "Монгол улс", "AS", "Ulan Bator", Lists.newArrayList("976"), Lists.newArrayList("MNT"), Lists.newArrayList("mn")),
    MO("澳门", "Macao", "澳門", "AS", "", Lists.newArrayList("853"), Lists.newArrayList("MOP"), Lists.newArrayList("zh", "pt")),
    MP("北马里亚纳群岛", "Northern Mariana Islands", "Northern Mariana Islands", "OC", "Saipan", Lists.newArrayList("1670"), Lists.newArrayList("USD"), Lists.newArrayList("en", "ch")),
    MQ("马提尼克", "Martinique", "Martinique", "NA", "Fort-de-France", Lists.newArrayList("596"), Lists.newArrayList("EUR"), Lists.newArrayList("fr")),
    MR("毛里塔尼亚", "Mauritania", "موريتانيا", "AF", "Nouakchott", Lists.newArrayList("222"), Lists.newArrayList("MRU"), Lists.newArrayList("ar")),
    MS("蒙特塞拉特", "Montserrat", "Montserrat", "NA", "Plymouth", Lists.newArrayList("1664"), Lists.newArrayList("XCD"), Lists.newArrayList("en")),
    MT("马耳他", "Malta", "Malta", "EU", "Valletta", Lists.newArrayList("356"), Lists.newArrayList("EUR"), Lists.newArrayList("mt", "en")),
    MU("毛里求斯", "Mauritius", "Maurice", "AF", "Port Louis", Lists.newArrayList("230"), Lists.newArrayList("MUR"), Lists.newArrayList("en")),
    MV("马尔代夫", "Maldives", "Maldives", "AS", "Malé", Lists.newArrayList("960"), Lists.newArrayList("MVR"), Lists.newArrayList("dv")),
    MW("马拉维", "Malawi", "Malawi", "AF", "Lilongwe", Lists.newArrayList("265"), Lists.newArrayList("MWK"), Lists.newArrayList("en", "ny")),
    MX("墨西哥", "Mexico", "México", "NA", "Mexico City", Lists.newArrayList("52"), Lists.newArrayList("MXN"), Lists.newArrayList("es")),
    MY("马来西亚", "Malaysia", "Malaysia", "AS", "Kuala Lumpur", Lists.newArrayList("60"), Lists.newArrayList("MYR"), Lists.newArrayList("ms")),
    MZ("莫桑比克", "Mozambique", "Moçambique", "AF", "Maputo", Lists.newArrayList("258"), Lists.newArrayList("MZN"), Lists.newArrayList("pt")),
    NA("纳米比亚", "Namibia", "Namibia", "AF", "Windhoek", Lists.newArrayList("264"), Lists.newArrayList("NAD", "ZAR"), Lists.newArrayList("en", "af")),
    NC("新喀里多尼亚", "New Caledonia", "Nouvelle-Calédonie", "OC", "Nouméa", Lists.newArrayList("687"), Lists.newArrayList("XPF"), Lists.newArrayList("fr")),
    NE("尼日尔", "Niger", "Niger", "AF", "Niamey", Lists.newArrayList("227"), Lists.newArrayList("XOF"), Lists.newArrayList("fr")),
    NF("诺福克岛", "Norfolk Island", "Norfolk Island", "OC", "Kingston", Lists.newArrayList("672"), Lists.newArrayList("AUD"), Lists.newArrayList("en")),
    NG("尼日利亚", "Nigeria", "Nigeria", "AF", "Abuja", Lists.newArrayList("234"), Lists.newArrayList("NGN"), Lists.newArrayList("en")),
    NI("尼加拉瓜", "Nicaragua", "Nicaragua", "NA", "Managua", Lists.newArrayList("505"), Lists.newArrayList("NIO"), Lists.newArrayList("es")),
    NL("荷兰", "Netherlands", "Nederland", "EU", "Amsterdam", Lists.newArrayList("31"), Lists.newArrayList("EUR"), Lists.newArrayList("nl")),
    NO("挪威", "Norway", "Norge", "EU", "Oslo", Lists.newArrayList("47"), Lists.newArrayList("NOK"), Lists.newArrayList("no", "nb", "nn")),
    NP("尼泊尔", "Nepal", "नपल", "AS", "Kathmandu", Lists.newArrayList("977"), Lists.newArrayList("NPR"), Lists.newArrayList("ne")),
    NR("瑙鲁", "Nauru", "Nauru", "OC", "Yaren", Lists.newArrayList("674"), Lists.newArrayList("AUD"), Lists.newArrayList("en", "na")),
    NU("纽埃", "Niue", "Niuē", "OC", "Alofi", Lists.newArrayList("683"), Lists.newArrayList("NZD"), Lists.newArrayList("en")),
    NZ("新西兰", "New Zealand", "New Zealand", "OC", "Wellington", Lists.newArrayList("64"), Lists.newArrayList("NZD"), Lists.newArrayList("en", "mi")),
    OM("阿曼", "Oman", "عمان", "AS", "Muscat", Lists.newArrayList("968"), Lists.newArrayList("OMR"), Lists.newArrayList("ar")),
    PA("巴拿马", "Panama", "Panamá", "NA", "Panama City", Lists.newArrayList("507"), Lists.newArrayList("PAB", "USD"), Lists.newArrayList("es")),
    PE("秘鲁", "Peru", "Perú", "SA", "Lima", Lists.newArrayList("51"), Lists.newArrayList("PEN"), Lists.newArrayList("es")),
    PF("法属波利尼西亚", "French Polynesia", "Polynésie française", "OC", "Papeetē", Lists.newArrayList("689"), Lists.newArrayList("XPF"), Lists.newArrayList("fr")),
    PG("巴布亚新几内亚", "Papua New Guinea", "Papua Niugini", "OC", "Port Moresby", Lists.newArrayList("675"), Lists.newArrayList("PGK"), Lists.newArrayList("en")),
    PH("菲律宾", "Philippines", "Pilipinas", "AS", "Manila", Lists.newArrayList("63"), Lists.newArrayList("PHP"), Lists.newArrayList("en")),
    PK("巴基斯坦", "Pakistan", "Pakistan", "AS", "Islamabad", Lists.newArrayList("92"), Lists.newArrayList("PKR"), Lists.newArrayList("en", "ur")),
    PL("波兰", "Poland", "Polska", "EU", "Warsaw", Lists.newArrayList("48"), Lists.newArrayList("PLN"), Lists.newArrayList("pl")),
    PM("圣皮埃尔和密克隆", "Saint Pierre and Miquelon", "Saint-Pierre-et-Miquelon", "NA", "Saint-Pierre", Lists.newArrayList("508"), Lists.newArrayList("EUR"), Lists.newArrayList("fr")),
    PN("皮特凯恩群岛", "Pitcairn Islands", "Pitcairn Islands", "OC", "Adamstown", Lists.newArrayList("64"), Lists.newArrayList("NZD"), Lists.newArrayList("en")),
    PR("波多黎各", "Puerto Rico", "Puerto Rico", "NA", "San Juan", Lists.newArrayList("1787", "1939"), Lists.newArrayList("USD"), Lists.newArrayList("es", "en")),
    PS("巴勒斯坦", "Palestine", "فلسطين", "AS", "Ramallah", Lists.newArrayList("970"), Lists.newArrayList("ILS"), Lists.newArrayList("ar")),
    PT("葡萄牙", "Portugal", "Portugal", "EU", "Lisbon", Lists.newArrayList("351"), Lists.newArrayList("EUR"), Lists.newArrayList("pt")),
    PW("帕劳", "Palau", "Palau", "OC", "Ngerulmud", Lists.newArrayList("680"), Lists.newArrayList("USD"), Lists.newArrayList("en")),
    PY("巴拉圭", "Paraguay", "Paraguay", "SA", "Asunción", Lists.newArrayList("595"), Lists.newArrayList("PYG"), Lists.newArrayList("es", "gn")),
    QA("卡塔尔", "Qatar", "قطر", "AS", "Doha", Lists.newArrayList("974"), Lists.newArrayList("QAR"), Lists.newArrayList("ar")),
    RE("留尼汪", "Réunion", "La Réunion", "AF", "Saint-Denis", Lists.newArrayList("262"), Lists.newArrayList("EUR"), Lists.newArrayList("fr")),
    RO("罗马尼亚", "Romania", "România", "EU", "Bucharest", Lists.newArrayList("40"), Lists.newArrayList("RON"), Lists.newArrayList("ro")),
    RS("塞尔维亚", "Serbia", "Србија", "EU", "Belgrade", Lists.newArrayList("381"), Lists.newArrayList("RSD"), Lists.newArrayList("sr")),
    RU("俄罗斯", "Russia", "Россия", "EU", "Moscow", Lists.newArrayList("7"), Lists.newArrayList("RUB"), Lists.newArrayList("ru")),
    RW("卢旺达", "Rwanda", "Rwanda", "AF", "Kigali", Lists.newArrayList("250"), Lists.newArrayList("RWF"), Lists.newArrayList("rw", "en", "fr")),
    SA("沙特阿拉伯", "Saudi Arabia", "العربية السعودية", "AS", "Riyadh", Lists.newArrayList("966"), Lists.newArrayList("SAR"), Lists.newArrayList("ar")),
    SB("所罗门群岛", "Solomon Islands", "Solomon Islands", "OC", "Honiara", Lists.newArrayList("677"), Lists.newArrayList("SBD"), Lists.newArrayList("en")),
    SC("塞舌尔", "Seychelles", "Seychelles", "AF", "Victoria", Lists.newArrayList("248"), Lists.newArrayList("SCR"), Lists.newArrayList("fr", "en")),
    SD("苏丹", "Sudan", "السودان", "AF", "Khartoum", Lists.newArrayList("249"), Lists.newArrayList("SDG"), Lists.newArrayList("ar", "en")),
    SE("瑞典", "Sweden", "Sverige", "EU", "Stockholm", Lists.newArrayList("46"), Lists.newArrayList("SEK"), Lists.newArrayList("sv")),
    SG("新加坡", "Singapore", "Singapore", "AS", "Singapore", Lists.newArrayList("65"), Lists.newArrayList("SGD"), Lists.newArrayList("en", "ms", "ta", "zh")),
    SH("圣赫勒拿、阿森松和特里斯坦-达库尼亚", "Saint Helena", "Saint Helena", "AF", "Jamestown", Lists.newArrayList("290"), Lists.newArrayList("SHP"), Lists.newArrayList("en")),
    SI("斯洛文尼亚", "Slovenia", "Slovenija", "EU", "Ljubljana", Lists.newArrayList("386"), Lists.newArrayList("EUR"), Lists.newArrayList("sl")),
    SJ("斯瓦尔巴和扬马延", "Svalbard and Jan Mayen", "Svalbard og Jan Mayen", "EU", "Longyearbyen", Lists.newArrayList("4779"), Lists.newArrayList("NOK"), Lists.newArrayList("no")),
    SK("斯洛伐克", "Slovakia", "Slovensko", "EU", "Bratislava", Lists.newArrayList("421"), Lists.newArrayList("EUR"), Lists.newArrayList("sk")),
    SL("塞拉利昂", "Sierra Leone", "Sierra Leone", "AF", "Freetown", Lists.newArrayList("232"), Lists.newArrayList("SLL"), Lists.newArrayList("en")),
    SM("圣马力诺", "San Marino", "San Marino", "EU", "City of San Marino", Lists.newArrayList("378"), Lists.newArrayList("EUR"), Lists.newArrayList("it")),
    SN("塞内加尔", "Senegal", "Sénégal", "AF", "Dakar", Lists.newArrayList("221"), Lists.newArrayList("XOF"), Lists.newArrayList("fr")),
    SO("索马里", "Somalia", "Soomaaliya", "AF", "Mogadishu", Lists.newArrayList("252"), Lists.newArrayList("SOS"), Lists.newArrayList("so", "ar")),
    SR("苏里南", "Suriname", "Suriname", "SA", "Paramaribo", Lists.newArrayList("597"), Lists.newArrayList("SRD"), Lists.newArrayList("nl")),
    SS("南苏丹", "South Sudan", "South Sudan", "AF", "Juba", Lists.newArrayList("211"), Lists.newArrayList("SSP"), Lists.newArrayList("en")),
    ST("圣多美和普林西比", "São Tomé and Príncipe", "São Tomé e Príncipe", "AF", "São Tomé", Lists.newArrayList("239"), Lists.newArrayList("STN"), Lists.newArrayList("pt")),
    SV("萨尔瓦多", "El Salvador", "El Salvador", "NA", "San Salvador", Lists.newArrayList("503"), Lists.newArrayList("SVC", "USD"), Lists.newArrayList("es")),
    SX("荷属圣马丁", "Sint Maarten", "Sint Maarten", "NA", "Philipsburg", Lists.newArrayList("1721"), Lists.newArrayList("ANG"), Lists.newArrayList("nl", "en")),
    SY("叙利亚", "Syria", "سوريا", "AS", "Damascus", Lists.newArrayList("963"), Lists.newArrayList("SYP"), Lists.newArrayList("ar")),
    SZ("斯威士兰", "Swaziland", "Swaziland", "AF", "Lobamba", Lists.newArrayList("268"), Lists.newArrayList("SZL"), Lists.newArrayList("en", "ss")),
    TC("特克斯和凯科斯群岛", "Turks and Caicos Islands", "Turks and Caicos Islands", "NA", "Cockburn Town", Lists.newArrayList("1649"), Lists.newArrayList("USD"), Lists.newArrayList("en")),
    TD("乍得", "Chad", "Tchad", "AF", "N'Djamena", Lists.newArrayList("235"), Lists.newArrayList("XAF"), Lists.newArrayList("fr", "ar")),
    TF("法属南部和南极领地", "French Southern Territories", "Territoire des Terres australes et antarctiques fr", "AN", "Port-aux-Français", Lists.newArrayList("262"), Lists.newArrayList("EUR"), Lists.newArrayList("fr")),
    TG("多哥", "Togo", "Togo", "AF", "Lomé", Lists.newArrayList("228"), Lists.newArrayList("XOF"), Lists.newArrayList("fr")),
    TH("泰国", "Thailand", "ประเทศไทย", "AS", "Bangkok", Lists.newArrayList("66"), Lists.newArrayList("THB"), Lists.newArrayList("th")),
    TJ("塔吉克斯坦", "Tajikistan", "Тоҷикистон", "AS", "Dushanbe", Lists.newArrayList("992"), Lists.newArrayList("TJS"), Lists.newArrayList("tg", "ru")),
    TK("托克劳", "Tokelau", "Tokelau", "OC", "Fakaofo", Lists.newArrayList("690"), Lists.newArrayList("NZD"), Lists.newArrayList("en")),
    TL("东帝汶", "East Timor", "Timor-Leste", "OC", "Dili", Lists.newArrayList("670"), Lists.newArrayList("USD"), Lists.newArrayList("pt")),
    TM("土库曼斯坦", "Turkmenistan", "Türkmenistan", "AS", "Ashgabat", Lists.newArrayList("993"), Lists.newArrayList("TMT"), Lists.newArrayList("tk", "ru")),
    TN("突尼斯", "Tunisia", "تونس", "AF", "Tunis", Lists.newArrayList("216"), Lists.newArrayList("TND"), Lists.newArrayList("ar")),
    TO("汤加", "Tonga", "Tonga", "OC", "Nuku'alofa", Lists.newArrayList("676"), Lists.newArrayList("TOP"), Lists.newArrayList("en", "to")),
    TR("土耳其", "Turkey", "Türkiye", "AS", "Ankara", Lists.newArrayList("90"), Lists.newArrayList("TRY"), Lists.newArrayList("tr")),
    TT("特立尼达和多巴哥", "Trinidad and Tobago", "Trinidad and Tobago", "NA", "Port of Spain", Lists.newArrayList("1868"), Lists.newArrayList("TTD"), Lists.newArrayList("en")),
    TV("图瓦卢", "Tuvalu", "Tuvalu", "OC", "Funafuti", Lists.newArrayList("688"), Lists.newArrayList("AUD"), Lists.newArrayList("en")),
    TW("中国台湾省", "Taiwan", "臺灣", "AS", "Taipei", Lists.newArrayList("886"), Lists.newArrayList("TWD"), Lists.newArrayList("zh")),
    TZ("坦桑尼亚", "Tanzania", "Tanzania", "AF", "Dodoma", Lists.newArrayList("255"), Lists.newArrayList("TZS"), Lists.newArrayList("sw", "en")),
    UA("乌克兰", "Ukraine", "Україна", "EU", "Kyiv", Lists.newArrayList("380"), Lists.newArrayList("UAH"), Lists.newArrayList("uk")),
    UG("乌干达", "Uganda", "Uganda", "AF", "Kampala", Lists.newArrayList("256"), Lists.newArrayList("UGX"), Lists.newArrayList("en", "sw")),
    UM("美国本土外小岛屿", "U.S. Minor Outlying Islands", "United States Minor Outlying Islands", "OC", "", Lists.newArrayList("1"), Lists.newArrayList("USD"), Lists.newArrayList("en")),
    US("美国", "United States", "United States", "NA", "Washington D.C.", Lists.newArrayList("1"), Lists.newArrayList("USD", "USN", "USS"), Lists.newArrayList("en")),
    UY("乌拉圭", "Uruguay", "Uruguay", "SA", "Montevideo", Lists.newArrayList("598"), Lists.newArrayList("UYI", "UYU"), Lists.newArrayList("es")),
    UZ("乌兹别克斯坦", "Uzbekistan", "O‘zbekiston", "AS", "Tashkent", Lists.newArrayList("998"), Lists.newArrayList("UZS"), Lists.newArrayList("uz", "ru")),
    VA("梵蒂冈", "Vatican City", "Vaticano", "EU", "Vatican City", Lists.newArrayList("379"), Lists.newArrayList("EUR"), Lists.newArrayList("it", "la")),
    VC("圣文森特和格林纳丁斯", "Saint Vincent and the Grenadines", "Saint Vincent and the Grenadines", "NA", "Kingstown", Lists.newArrayList("1784"), Lists.newArrayList("XCD"), Lists.newArrayList("en")),
    VE("委内瑞拉", "Venezuela", "Venezuela", "SA", "Caracas", Lists.newArrayList("58"), Lists.newArrayList("VES"), Lists.newArrayList("es")),
    VG("英属维尔京群岛", "British Virgin Islands", "British Virgin Islands", "NA", "Road Town", Lists.newArrayList("1284"), Lists.newArrayList("USD"), Lists.newArrayList("en")),
    VI("美属维尔京群岛", "U.S. Virgin Islands", "United States Virgin Islands", "NA", "Charlotte Amalie", Lists.newArrayList("1340"), Lists.newArrayList("USD"), Lists.newArrayList("en")),
    VN("越南", "Vietnam", "Việt Nam", "AS", "Hanoi", Lists.newArrayList("84"), Lists.newArrayList("VND"), Lists.newArrayList("vi")),
    VU("瓦努阿图", "Vanuatu", "Vanuatu", "OC", "Port Vila", Lists.newArrayList("678"), Lists.newArrayList("VUV"), Lists.newArrayList("bi", "en", "fr")),
    WF("瓦利斯和富图纳", "Wallis and Futuna", "Wallis et Futuna", "OC", "Mata-Utu", Lists.newArrayList("681"), Lists.newArrayList("XPF"), Lists.newArrayList("fr")),
    WS("萨摩亚", "Samoa", "Samoa", "OC", "Apia", Lists.newArrayList("685"), Lists.newArrayList("WST"), Lists.newArrayList("sm", "en")),
    XK("科索沃", "Kosovo", "Republika e Kosovës", "EU", "Pristina", Lists.newArrayList("377", "381", "383", "386"), Lists.newArrayList("EUR"), Lists.newArrayList("sq", "sr")),
    YE("也门", "Yemen", "اليَمَن", "AS", "Sana'a", Lists.newArrayList("967"), Lists.newArrayList("YER"), Lists.newArrayList("ar")),
    YT("马约特", "Mayotte", "Mayotte", "AF", "Mamoudzou", Lists.newArrayList("262"), Lists.newArrayList("EUR"), Lists.newArrayList("fr")),
    ZA("南非", "South Africa", "South Africa", "AF", "Pretoria", Lists.newArrayList("27"), Lists.newArrayList("ZAR"), Lists.newArrayList("af", "en", "nr", "st", "ss", "tn", "ts", "ve", "xh", "zu")),
    ZM("赞比亚", "Zambia", "Zambia", "AF", "Lusaka", Lists.newArrayList("260"), Lists.newArrayList("ZMW"), Lists.newArrayList("en")),
    ZW("津巴布韦", "Zimbabwe", "Zimbabwe", "AF", "Harare", Lists.newArrayList("263"), Lists.newArrayList("USD", "ZAR", "BWP", "GBP", "AUD", "CNY", "INR", "JPY"), Lists.newArrayList("en", "sn", "nd"));

    private final String chineseName;
    private final String name;
    private final String nativeName;
    private final String continent;
    private final String capital;
    private final List<String> phone;
    private final List<String> currency;
    private final List<String> languages;
    private static final Map<String, CountryEnum> COUNTRY_NAME_MAP = Arrays.stream(CountryEnum.values())
            .collect(Collectors.toMap(CountryEnum::name, Function.identity()));

    public static CountryEnum ofName(String iso) {
        return COUNTRY_NAME_MAP.get(iso);
    }

    public static final Map<String, String> COUNTRY_CENTER_LAT_LNG = new HashMap<>();

    CountryEnum(String chineseName, String name, String nativeName, String continent, String capital, List<String> phone, List<String> currency, List<String> languages) {
        this.chineseName = chineseName;
        this.name = name;
        this.nativeName = nativeName;
        this.continent = continent;
        this.capital = capital;
        this.phone = phone;
        this.currency = currency;
        this.languages = languages;
    }

    static {
        COUNTRY_CENTER_LAT_LNG.put("AD",  "42.56,1.56");
        COUNTRY_CENTER_LAT_LNG.put("AE",  "23.78,54.24");
        COUNTRY_CENTER_LAT_LNG.put("AF",  "34.45,66.8");
        COUNTRY_CENTER_LAT_LNG.put("AG",  "17.31,-62.05");
        COUNTRY_CENTER_LAT_LNG.put("AI",  "18.46,-63.21");
        COUNTRY_CENTER_LAT_LNG.put("AL",  "41.04,19.86");
        COUNTRY_CENTER_LAT_LNG.put("AM",  "40.17,45.18");
        COUNTRY_CENTER_LAT_LNG.put("AO",  "-11.95,17.58");
        COUNTRY_CENTER_LAT_LNG.put("AR",  "-37.27,-65.39");
        COUNTRY_CENTER_LAT_LNG.put("AT",  "47.57,13.34");
        COUNTRY_CENTER_LAT_LNG.put("AU",  "-28.83,134.3");
        COUNTRY_CENTER_LAT_LNG.put("AZ",  "40.17,47.29");
        COUNTRY_CENTER_LAT_LNG.put("BA",  "44.03,17.75");
        COUNTRY_CENTER_LAT_LNG.put("BB",  "13.27,-59.59");
        COUNTRY_CENTER_LAT_LNG.put("BD",  "23.82,90.53");
        COUNTRY_CENTER_LAT_LNG.put("BE",  "50.71,4.39");
        COUNTRY_CENTER_LAT_LNG.put("BF",  "11.95,-2.11");
        COUNTRY_CENTER_LAT_LNG.put("BG",  "42.89,24.61");
        COUNTRY_CENTER_LAT_LNG.put("BH",  "26.07,50.55");
        COUNTRY_CENTER_LAT_LNG.put("BI",  "-3.25,30.06");
        COUNTRY_CENTER_LAT_LNG.put("BJ",  "9.4,2.29");
        COUNTRY_CENTER_LAT_LNG.put("BM",  "32.37,-64.8");
        COUNTRY_CENTER_LAT_LNG.put("BN",  "4.52,114.57");
        COUNTRY_CENTER_LAT_LNG.put("BO",  "-16.17,-63.98");
        COUNTRY_CENTER_LAT_LNG.put("BR",  "-10.55,-56.95");
        COUNTRY_CENTER_LAT_LNG.put("BT",  "27.33,90.53");
        COUNTRY_CENTER_LAT_LNG.put("BW",  "-21.8,24.61");
        COUNTRY_CENTER_LAT_LNG.put("BY",  "54.14,27.42");
        COUNTRY_CENTER_LAT_LNG.put("BZ",  "17.14,-88.42");
        COUNTRY_CENTER_LAT_LNG.put("CA",  "59.97,-113.39");
        COUNTRY_CENTER_LAT_LNG.put("CD",  "-3.52,23.2");
        COUNTRY_CENTER_LAT_LNG.put("CF",  "6.33,20.39");
        COUNTRY_CENTER_LAT_LNG.put("CH",  "46.67,8.26");
        COUNTRY_CENTER_LAT_LNG.put("CI",  "7.73,-4.92");
        COUNTRY_CENTER_LAT_LNG.put("CK",  "-18.63,-159.8");
        COUNTRY_CENTER_LAT_LNG.put("CL",  "-37.88,-71.54");
        COUNTRY_CENTER_LAT_LNG.put("CM",  "6.33,13.36");
        COUNTRY_CENTER_LAT_LNG.put("CN",  "32.80,109.52");
        COUNTRY_CENTER_LAT_LNG.put("CO",  "4.92,-73.83");
        COUNTRY_CENTER_LAT_LNG.put("CR",  "9.4,-84.55");
        COUNTRY_CENTER_LAT_LNG.put("CU",  "21.53,-79.28");
        COUNTRY_CENTER_LAT_LNG.put("CV",  "16.08,-24.08");
        COUNTRY_CENTER_LAT_LNG.put("CY",  "35.13,33.46");
        COUNTRY_CENTER_LAT_LNG.put("CZ",  "49.66,15.64");
        COUNTRY_CENTER_LAT_LNG.put("DE",  "51.33,10.55");
        COUNTRY_CENTER_LAT_LNG.put("DJ",  "11.87,42.71");
        COUNTRY_CENTER_LAT_LNG.put("DK",  "55.96,11.1");
        COUNTRY_CENTER_LAT_LNG.put("DM",  "15.38,-61.35");
        COUNTRY_CENTER_LAT_LNG.put("DO",  "19.25,-70.49");
        COUNTRY_CENTER_LAT_LNG.put("DZ",  "28.83,2.11");
        COUNTRY_CENTER_LAT_LNG.put("EC",  "-0.99,-79.03");
        COUNTRY_CENTER_LAT_LNG.put("EE",  "58.62,25.14");
        COUNTRY_CENTER_LAT_LNG.put("EG",  "26.02,30.23");
        COUNTRY_CENTER_LAT_LNG.put("ER",  "15.03,39.55");
        COUNTRY_CENTER_LAT_LNG.put("ES",  "39.75,-3.24");
        COUNTRY_CENTER_LAT_LNG.put("ET",  "9.14,40.08");
        COUNTRY_CENTER_LAT_LNG.put("FI",  "62.65,25.95");
        COUNTRY_CENTER_LAT_LNG.put("FJ",  "-17.76,177.99");
        COUNTRY_CENTER_LAT_LNG.put("FK",  "-51.94,-59.59");
        COUNTRY_CENTER_LAT_LNG.put("FM",  "6.88,158.24");
        COUNTRY_CENTER_LAT_LNG.put("FO",  "61.96,-6.86");
        COUNTRY_CENTER_LAT_LNG.put("FR",  "4.2,-51.66");
        COUNTRY_CENTER_LAT_LNG.put("GA",  "-0.7,11.95");
        COUNTRY_CENTER_LAT_LNG.put("GB",  "55.55,-3.52");
        COUNTRY_CENTER_LAT_LNG.put("GD",  "12.22,-61.7");
        COUNTRY_CENTER_LAT_LNG.put("GE",  "42.1,43.42");
        COUNTRY_CENTER_LAT_LNG.put("GG",  "49.45,-2.58");
        COUNTRY_CENTER_LAT_LNG.put("GH",  "7.82,-1.58");
        COUNTRY_CENTER_LAT_LNG.put("GI",  "36.1,-5.34");
        COUNTRY_CENTER_LAT_LNG.put("GL",  "75.94,-39.38");
        COUNTRY_CENTER_LAT_LNG.put("GM",  "13.52,-15.43");
        COUNTRY_CENTER_LAT_LNG.put("GN",  "10.28,-10.72");
        COUNTRY_CENTER_LAT_LNG.put("GQ",  "1.57,10.35");
        COUNTRY_CENTER_LAT_LNG.put("GR",  "38.17,24.32");
        COUNTRY_CENTER_LAT_LNG.put("GS",  "-56.65,-28.46");
        COUNTRY_CENTER_LAT_LNG.put("GT",  "15.73,-90.53");
        COUNTRY_CENTER_LAT_LNG.put("GW",  "11.87,-15.29");
        COUNTRY_CENTER_LAT_LNG.put("GY",  "4.83,-58.89");
        COUNTRY_CENTER_LAT_LNG.put("HN",  "15.21,-85.25");
        COUNTRY_CENTER_LAT_LNG.put("HR",  "45.13,15.31");
        COUNTRY_CENTER_LAT_LNG.put("HT",  "18.79,-73.15");
        COUNTRY_CENTER_LAT_LNG.put("HU",  "47.37,19.51");
        COUNTRY_CENTER_LAT_LNG.put("ID",  "-1.12,114.16");
        COUNTRY_CENTER_LAT_LNG.put("IE",  "53.53,-8.26");
        COUNTRY_CENTER_LAT_LNG.put("IL",  "31.97,34.83");
        COUNTRY_CENTER_LAT_LNG.put("IM",  "54.12,-4.55");
        COUNTRY_CENTER_LAT_LNG.put("IN",  "21.8,82.27");
        COUNTRY_CENTER_LAT_LNG.put("IO",  "-6.17,71.59");
        COUNTRY_CENTER_LAT_LNG.put("IQ",  "33.05,44.3");
        COUNTRY_CENTER_LAT_LNG.put("IR",  "33.05,54.14");
        COUNTRY_CENTER_LAT_LNG.put("IS",  "65.39,-18.98");
        COUNTRY_CENTER_LAT_LNG.put("IT",  "41.2,12.5");
        COUNTRY_CENTER_LAT_LNG.put("JE",  "49.13,-2.29");
        COUNTRY_CENTER_LAT_LNG.put("JM",  "17.67,-77.17");
        COUNTRY_CENTER_LAT_LNG.put("JO",  "31.78,36.41");
        COUNTRY_CENTER_LAT_LNG.put("JP",  "36.25,138.68");
        COUNTRY_CENTER_LAT_LNG.put("KE",  "0.7,38.67");
        COUNTRY_CENTER_LAT_LNG.put("KG",  "41.22,74");
        COUNTRY_CENTER_LAT_LNG.put("KH",  "11.95,104.77");
        COUNTRY_CENTER_LAT_LNG.put("KI",  "-10.03,-150.01");
        COUNTRY_CENTER_LAT_LNG.put("KM",  "-12.04,43.77");
        COUNTRY_CENTER_LAT_LNG.put("KN",  "17.2,-62.69");
        COUNTRY_CENTER_LAT_LNG.put("KP",  "39.72,126.28");
        COUNTRY_CENTER_LAT_LNG.put("KR",  "36.49,128.08");
        COUNTRY_CENTER_LAT_LNG.put("KW",  "29.27,47.99");
        COUNTRY_CENTER_LAT_LNG.put("KY",  "19.6,-80.3");
        COUNTRY_CENTER_LAT_LNG.put("KZ",  "47.11,65.39");
        COUNTRY_CENTER_LAT_LNG.put("LA",  "19.34,102.97");
        COUNTRY_CENTER_LAT_LNG.put("LB",  "33.9,35.88");
        COUNTRY_CENTER_LAT_LNG.put("LC",  "13.97,-61");
        COUNTRY_CENTER_LAT_LNG.put("LI",  "47.13,9.56");
        COUNTRY_CENTER_LAT_LNG.put("LK",  "7.73,80.86");
        COUNTRY_CENTER_LAT_LNG.put("LR",  "6.42,-9.32");
        COUNTRY_CENTER_LAT_LNG.put("LS",  "-29.62,28.3");
        COUNTRY_CENTER_LAT_LNG.put("LT",  "55.28,23.73");
        COUNTRY_CENTER_LAT_LNG.put("LU",  "49.77,6.09");
        COUNTRY_CENTER_LAT_LNG.put("LV",  "56.87,24.79");
        COUNTRY_CENTER_LAT_LNG.put("LY",  "27.42,16.17");
        COUNTRY_CENTER_LAT_LNG.put("MA",  "31.62,-7.55");
        COUNTRY_CENTER_LAT_LNG.put("MC",  "43.63,7.48");
        COUNTRY_CENTER_LAT_LNG.put("MD",  "47.2,28.3");
        COUNTRY_CENTER_LAT_LNG.put("ME",  "42.69,19.36");
        COUNTRY_CENTER_LAT_LNG.put("MG",  "-18.98,47.11");
        COUNTRY_CENTER_LAT_LNG.put("MH",  "8.97,167.96");
        COUNTRY_CENTER_LAT_LNG.put("MK",  "41.57,21.62");
        COUNTRY_CENTER_LAT_LNG.put("ML",  "16.79,-4.39");
        COUNTRY_CENTER_LAT_LNG.put("MM",  "21.66,96.44");
        COUNTRY_CENTER_LAT_LNG.put("MN",  "47.11,103.36");
        COUNTRY_CENTER_LAT_LNG.put("MR",  "20.39,-10.55");
        COUNTRY_CENTER_LAT_LNG.put("MS",  "16.68,-62.2");
        COUNTRY_CENTER_LAT_LNG.put("MT",  "35.93,14.44");
        COUNTRY_CENTER_LAT_LNG.put("MU",  "-16.99,59.36");
        COUNTRY_CENTER_LAT_LNG.put("MV",  "3.25,73.3");
        COUNTRY_CENTER_LAT_LNG.put("MW",  "-13.1,34.28");
        COUNTRY_CENTER_LAT_LNG.put("MX",  "23.2,-101.95");
        COUNTRY_CENTER_LAT_LNG.put("MY",  "2.23,113.13");
        COUNTRY_CENTER_LAT_LNG.put("MZ",  "-17.84,35.33");
        COUNTRY_CENTER_LAT_LNG.put("NA",  "-21.8,17.58");
        COUNTRY_CENTER_LAT_LNG.put("NE",  "17.58,7.73");
        COUNTRY_CENTER_LAT_LNG.put("NG",  "9.14,9.14");
        COUNTRY_CENTER_LAT_LNG.put("NI",  "12.92,-84.9");
        COUNTRY_CENTER_LAT_LNG.put("NL",  "51.55,3.09");
        COUNTRY_CENTER_LAT_LNG.put("NO",  "61.71,8.88");
        COUNTRY_CENTER_LAT_LNG.put("NP",  "28.39,83.85");
        COUNTRY_CENTER_LAT_LNG.put("NR",  "-0.51,166.93");
        COUNTRY_CENTER_LAT_LNG.put("NU",  "-19.05,-169.87");
        COUNTRY_CENTER_LAT_LNG.put("NZ",  "-42.76,172.2");
        COUNTRY_CENTER_LAT_LNG.put("OM",  "21.01,56.07");
        COUNTRY_CENTER_LAT_LNG.put("PA",  "8.53,-80.33");
        COUNTRY_CENTER_LAT_LNG.put("PE",  "-9.14,-75.23");
        COUNTRY_CENTER_LAT_LNG.put("PG",  "-6.41,145.53");
        COUNTRY_CENTER_LAT_LNG.put("PH",  "12.1,123.26");
        COUNTRY_CENTER_LAT_LNG.put("PK",  "30.23,69.61");
        COUNTRY_CENTER_LAT_LNG.put("PL",  "51.33,18.98");
        COUNTRY_CENTER_LAT_LNG.put("PN",  "-24.5,-128.47");
        COUNTRY_CENTER_LAT_LNG.put("PS",  "31.88,35.25");
        COUNTRY_CENTER_LAT_LNG.put("PT",  "39.58,-8.66");
        COUNTRY_CENTER_LAT_LNG.put("PW",  "7.49,134.56");
        COUNTRY_CENTER_LAT_LNG.put("PY",  "-23.2,-58.36");
        COUNTRY_CENTER_LAT_LNG.put("QA",  "25.31,51.18");
        COUNTRY_CENTER_LAT_LNG.put("RO",  "45.7,24.61");
        COUNTRY_CENTER_LAT_LNG.put("RS",  "44.21,21.27");
        COUNTRY_CENTER_LAT_LNG.put("RU",  "64.69,84.38");
        COUNTRY_CENTER_LAT_LNG.put("RW",  "-2.02,30.06");
        COUNTRY_CENTER_LAT_LNG.put("SA",  "23.2,44.3");
        COUNTRY_CENTER_LAT_LNG.put("SB",  "-9.1,160.95");
        COUNTRY_CENTER_LAT_LNG.put("SC",  "-6.87,52.58");
        COUNTRY_CENTER_LAT_LNG.put("SD",  "14.77,30.23");
        COUNTRY_CENTER_LAT_LNG.put("SE",  "61.96,17.4");
        COUNTRY_CENTER_LAT_LNG.put("SG",  "1.35,103.86");
        COUNTRY_CENTER_LAT_LNG.put("SI",  "46.14,14.94");
        COUNTRY_CENTER_LAT_LNG.put("SK",  "48.78,19.86");
        COUNTRY_CENTER_LAT_LNG.put("SL",  "8.35,-11.78");
        COUNTRY_CENTER_LAT_LNG.put("SM",  "43.94,12.46");
        COUNTRY_CENTER_LAT_LNG.put("SN",  "13.97,-14.59");
        COUNTRY_CENTER_LAT_LNG.put("SO",  "5.95,45.9");
        COUNTRY_CENTER_LAT_LNG.put("SR",  "3.96,-56.43");
        COUNTRY_CENTER_LAT_LNG.put("SS",  "7.73,30.23");
        COUNTRY_CENTER_LAT_LNG.put("ST",  "0.26,6.6");
        COUNTRY_CENTER_LAT_LNG.put("SV",  "13.62,-88.77");
        COUNTRY_CENTER_LAT_LNG.put("SY",  "34.89,37.79");
        COUNTRY_CENTER_LAT_LNG.put("TC",  "21.53,-71.89");
        COUNTRY_CENTER_LAT_LNG.put("TD",  "14.77,17.58");
        COUNTRY_CENTER_LAT_LNG.put("TG",  "8.7,0.88");
        COUNTRY_CENTER_LAT_LNG.put("TH",  "15,101.2");
        COUNTRY_CENTER_LAT_LNG.put("TJ",  "38.76,70.84");
        COUNTRY_CENTER_LAT_LNG.put("TK",  "-9.07,-171.8");
        COUNTRY_CENTER_LAT_LNG.put("TL",  "-8.88,125.68");
        COUNTRY_CENTER_LAT_LNG.put("TM",  "38.67,58.36");
        COUNTRY_CENTER_LAT_LNG.put("TN",  "34.19,10.02");
        COUNTRY_CENTER_LAT_LNG.put("TO",  "-19.75,-175.23");
        COUNTRY_CENTER_LAT_LNG.put("TR",  "38.67,34.45");
        COUNTRY_CENTER_LAT_LNG.put("TT",  "10.63,-61");
        COUNTRY_CENTER_LAT_LNG.put("TV",  "-5.72,175.87");
        COUNTRY_CENTER_LAT_LNG.put("TW",  "23.77,121");
        COUNTRY_CENTER_LAT_LNG.put("TZ",  "-6.33,34.45");
        COUNTRY_CENTER_LAT_LNG.put("UA",  "48.52,31.64");
        COUNTRY_CENTER_LAT_LNG.put("UG",  "1.49,32.17");
        COUNTRY_CENTER_LAT_LNG.put("US",  "39.67,-101.5");
        COUNTRY_CENTER_LAT_LNG.put("UY",  "-33.05,-55.55");
        COUNTRY_CENTER_LAT_LNG.put("UZ",  "41.4,64.86");
        COUNTRY_CENTER_LAT_LNG.put("VA",  "41.9,12.45");
        COUNTRY_CENTER_LAT_LNG.put("VC",  "13.03,-61.24");
        COUNTRY_CENTER_LAT_LNG.put("VE",  "7.73,-66.8");
        COUNTRY_CENTER_LAT_LNG.put("VG",  "18.54,-64.51");
        COUNTRY_CENTER_LAT_LNG.put("VN",  "13.92,108.24");
        COUNTRY_CENTER_LAT_LNG.put("VU",  "-16.4,167.54");
        COUNTRY_CENTER_LAT_LNG.put("WS",  "-13.8,-172.09");
        COUNTRY_CENTER_LAT_LNG.put("XK",  "42.63,20.92");
        COUNTRY_CENTER_LAT_LNG.put("YE",  "15.03,47.99");
        COUNTRY_CENTER_LAT_LNG.put("ZA",  "-28.83,26.02");
        COUNTRY_CENTER_LAT_LNG.put("ZM",  "-13.1,28.3");
        COUNTRY_CENTER_LAT_LNG.put("ZW",  "-18.98,30.23");
    }

    public String getChineseName() {
        return chineseName;
    }

    public String getName() {
        return name;
    }

    public String getNativeName() {
        return nativeName;
    }

    public String getContinent() {
        return continent;
    }

    public String getCapital() {
        return capital;
    }

    public List<String> getPhone() {
        return phone;
    }

    public List<String> getCurrency() {
        return currency;
    }

    public List<String> getLanguages() {
        return languages;
    }
}

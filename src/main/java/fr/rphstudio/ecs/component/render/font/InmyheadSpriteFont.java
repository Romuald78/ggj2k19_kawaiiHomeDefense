package fr.rphstudio.ecs.component.render.font; 
public class InmyheadSpriteFont { 
    private static FontCharInfo[] letters = { 
        new FontCharInfo(' ', 10, 0, 33, 109), 
        new FontCharInfo('!', 53, 0, 45, 114), 
        new FontCharInfo('"', 108, 0, 54, 109), 
        new FontCharInfo('#', 172, 0, 54, 109), 
        new FontCharInfo('$', 236, 0, 69, 115), 
        new FontCharInfo('%', 315, 0, 54, 109), 
        new FontCharInfo('&', 379, 0, 81, 111), 
        new FontCharInfo('\'', 470, 0, 37, 109), 
        new FontCharInfo('(', 517, 0, 54, 109), 
        new FontCharInfo(')', 581, 0, 54, 109), 
        new FontCharInfo('*', 645, 0, 81, 109), 
        new FontCharInfo('+', 736, 0, 54, 109), 
        new FontCharInfo(',', 800, 0, 42, 116), 
        new FontCharInfo('-', 852, 0, 52, 109), 
        new FontCharInfo('.', 914, 0, 38, 109), 
        new FontCharInfo('/', 962, 0, 56, 113), 
        new FontCharInfo('0', 1028, 0, 99, 109), 
        new FontCharInfo('1', 1137, 0, 47, 109), 
        new FontCharInfo('2', 1194, 0, 63, 109), 
        new FontCharInfo('3', 1267, 0, 64, 122), 
        new FontCharInfo('4', 1341, 0, 67, 109), 
        new FontCharInfo('5', 1418, 0, 60, 119), 
        new FontCharInfo('6', 10, 142, 91, 118), 
        new FontCharInfo('7', 111, 142, 63, 109), 
        new FontCharInfo('8', 184, 142, 76, 109), 
        new FontCharInfo('9', 270, 142, 82, 121), 
        new FontCharInfo(':', 362, 142, 37, 109), 
        new FontCharInfo(';', 409, 142, 45, 109), 
        new FontCharInfo('<', 464, 142, 54, 109), 
        new FontCharInfo('=', 528, 142, 54, 109), 
        new FontCharInfo('>', 592, 142, 54, 109), 
        new FontCharInfo('?', 656, 142, 66, 113), 
        new FontCharInfo('@', 732, 142, 54, 109), 
        new FontCharInfo('A', 796, 142, 81, 111), 
        new FontCharInfo('B', 887, 142, 60, 109), 
        new FontCharInfo('C', 957, 142, 78, 113), 
        new FontCharInfo('D', 1045, 142, 66, 109), 
        new FontCharInfo('E', 1121, 142, 68, 109), 
        new FontCharInfo('F', 1199, 142, 69, 109), 
        new FontCharInfo('G', 1278, 142, 75, 114), 
        new FontCharInfo('H', 1363, 142, 77, 109), 
        new FontCharInfo('I', 1450, 142, 36, 109), 
        new FontCharInfo('J', 10, 283, 45, 109), 
        new FontCharInfo('K', 65, 283, 81, 116), 
        new FontCharInfo('L', 156, 283, 62, 109), 
        new FontCharInfo('M', 228, 283, 110, 110), 
        new FontCharInfo('N', 348, 283, 98, 109), 
        new FontCharInfo('O', 456, 283, 121, 121), 
        new FontCharInfo('P', 587, 283, 67, 109), 
        new FontCharInfo('Q', 664, 283, 91, 114), 
        new FontCharInfo('R', 765, 283, 80, 109), 
        new FontCharInfo('S', 855, 283, 57, 109), 
        new FontCharInfo('T', 922, 283, 63, 109), 
        new FontCharInfo('U', 995, 283, 83, 109), 
        new FontCharInfo('V', 1088, 283, 63, 109), 
        new FontCharInfo('W', 1161, 283, 126, 126), 
        new FontCharInfo('X', 1297, 283, 99, 109), 
        new FontCharInfo('Y', 1406, 283, 74, 109), 
        new FontCharInfo('Z', 10, 429, 75, 109), 
        new FontCharInfo('[', 95, 429, 54, 109), 
        new FontCharInfo('\\', 159, 429, 54, 109), 
        new FontCharInfo(']', 223, 429, 54, 109), 
        new FontCharInfo('^', 287, 429, 54, 109), 
        new FontCharInfo('_', 351, 429, 70, 118), 
        new FontCharInfo('`', 431, 429, 54, 109), 
        new FontCharInfo('a', 495, 429, 75, 111), 
        new FontCharInfo('b', 580, 429, 70, 113), 
        new FontCharInfo('c', 660, 429, 70, 110), 
        new FontCharInfo('d', 740, 429, 64, 111), 
        new FontCharInfo('e', 814, 429, 67, 109), 
        new FontCharInfo('f', 891, 429, 67, 109), 
        new FontCharInfo('g', 968, 429, 91, 130), 
        new FontCharInfo('h', 1069, 429, 84, 109), 
        new FontCharInfo('i', 1163, 429, 42, 109), 
        new FontCharInfo('j', 1215, 429, 68, 124), 
        new FontCharInfo('k', 1293, 429, 74, 109), 
        new FontCharInfo('l', 1377, 429, 51, 109), 
        new FontCharInfo('m', 10, 579, 108, 115), 
        new FontCharInfo('n', 128, 579, 112, 117), 
        new FontCharInfo('o', 250, 579, 125, 130), 
        new FontCharInfo('p', 385, 579, 77, 115), 
        new FontCharInfo('q', 472, 579, 94, 121), 
        new FontCharInfo('r', 576, 579, 86, 113), 
        new FontCharInfo('s', 672, 579, 68, 116), 
        new FontCharInfo('t', 750, 579, 87, 116), 
        new FontCharInfo('u', 847, 579, 85, 112), 
        new FontCharInfo('v', 942, 579, 73, 119), 
        new FontCharInfo('w', 1025, 579, 118, 113), 
        new FontCharInfo('x', 1153, 579, 114, 119), 
        new FontCharInfo('y', 1277, 579, 73, 109), 
        new FontCharInfo('z', 1360, 579, 87, 117), 
        new FontCharInfo('{', 10, 729, 54, 109), 
        new FontCharInfo('|', 74, 729, 54, 109), 
        new FontCharInfo('}', 138, 729, 54, 109), 
        new FontCharInfo('~', 202, 729, 54, 109), 
        new FontCharInfo('¡', 266, 729, 54, 109), 
        new FontCharInfo('¢', 330, 729, 54, 109), 
        new FontCharInfo('£', 394, 729, 83, 109), 
        new FontCharInfo('¤', 487, 729, 54, 109), 
        new FontCharInfo('¥', 551, 729, 74, 112), 
        new FontCharInfo('¦', 635, 729, 54, 109), 
        new FontCharInfo('§', 699, 729, 54, 109), 
        new FontCharInfo('¨', 763, 729, 54, 109), 
        new FontCharInfo('©', 827, 729, 54, 109), 
        new FontCharInfo('ª', 891, 729, 54, 109), 
        new FontCharInfo('«', 955, 729, 54, 109), 
        new FontCharInfo('¬', 1019, 729, 54, 109), 
        new FontCharInfo('­', 1083, 729, 52, 109), 
        new FontCharInfo('®', 1145, 729, 54, 109), 
        new FontCharInfo('¯', 1209, 729, 54, 109), 
        new FontCharInfo('°', 1273, 729, 54, 109), 
        new FontCharInfo('±', 1337, 729, 54, 109), 
        new FontCharInfo('²', 1401, 729, 54, 109), 
        new FontCharInfo('³', 10, 861, 54, 109), 
        new FontCharInfo('´', 74, 861, 54, 109), 
        new FontCharInfo('µ', 138, 861, 54, 109), 
        new FontCharInfo('¶', 202, 861, 54, 109), 
        new FontCharInfo('·', 266, 861, 54, 109), 
        new FontCharInfo('¸', 330, 861, 54, 109), 
        new FontCharInfo('¹', 394, 861, 54, 109), 
        new FontCharInfo('º', 458, 861, 54, 109), 
        new FontCharInfo('»', 522, 861, 54, 109), 
        new FontCharInfo('¼', 586, 861, 54, 109), 
        new FontCharInfo('½', 650, 861, 128, 113), 
        new FontCharInfo('¾', 788, 861, 54, 109), 
        new FontCharInfo('¿', 852, 861, 54, 109), 
        new FontCharInfo('À', 916, 861, 54, 109), 
        new FontCharInfo('Á', 980, 861, 54, 109), 
        new FontCharInfo('Â', 1044, 861, 54, 109), 
        new FontCharInfo('Ã', 1108, 861, 54, 109), 
        new FontCharInfo('Ä', 1172, 861, 66, 109), 
        new FontCharInfo('Å', 1248, 861, 59, 109), 
        new FontCharInfo('Æ', 1317, 861, 54, 109), 
        new FontCharInfo('Ç', 1381, 861, 54, 109), 
        new FontCharInfo('È', 1445, 861, 54, 109), 
        new FontCharInfo('É', 10, 994, 54, 109), 
        new FontCharInfo('Ê', 74, 994, 54, 109), 
        new FontCharInfo('Ë', 138, 994, 54, 109), 
        new FontCharInfo('Ì', 202, 994, 54, 109), 
        new FontCharInfo('Í', 266, 994, 54, 109), 
        new FontCharInfo('Î', 330, 994, 54, 109), 
        new FontCharInfo('Ï', 394, 994, 54, 109), 
        new FontCharInfo('Ð', 458, 994, 54, 109), 
        new FontCharInfo('Ñ', 522, 994, 54, 109), 
        new FontCharInfo('Ò', 586, 994, 54, 109), 
        new FontCharInfo('Ó', 650, 994, 54, 109), 
        new FontCharInfo('Ô', 714, 994, 54, 109), 
        new FontCharInfo('Õ', 778, 994, 54, 109), 
        new FontCharInfo('Ö', 842, 994, 80, 114), 
        new FontCharInfo('×', 932, 994, 54, 109), 
        new FontCharInfo('Ø', 996, 994, 54, 109), 
        new FontCharInfo('Ù', 1060, 994, 54, 109), 
        new FontCharInfo('Ú', 1124, 994, 54, 109), 
        new FontCharInfo('Û', 1188, 994, 54, 109), 
        new FontCharInfo('Ü', 1252, 994, 54, 109), 
        new FontCharInfo('Ý', 1316, 994, 54, 109), 
        new FontCharInfo('Þ', 1380, 994, 54, 109), 
        new FontCharInfo('ß', 1444, 994, 54, 109), 
        new FontCharInfo('à', 10, 1128, 54, 109), 
        new FontCharInfo('á', 74, 1128, 54, 109), 
        new FontCharInfo('â', 138, 1128, 54, 109), 
        new FontCharInfo('ã', 202, 1128, 54, 109), 
        new FontCharInfo('ä', 266, 1128, 63, 109), 
        new FontCharInfo('å', 339, 1128, 57, 109), 
        new FontCharInfo('æ', 406, 1128, 54, 109), 
        new FontCharInfo('ç', 470, 1128, 54, 109), 
        new FontCharInfo('è', 534, 1128, 54, 109), 
        new FontCharInfo('é', 598, 1128, 54, 109), 
        new FontCharInfo('ê', 662, 1128, 54, 109), 
        new FontCharInfo('ë', 726, 1128, 54, 109), 
        new FontCharInfo('ì', 790, 1128, 54, 109), 
        new FontCharInfo('í', 854, 1128, 54, 109), 
        new FontCharInfo('î', 918, 1128, 54, 109), 
        new FontCharInfo('ï', 982, 1128, 54, 109), 
        new FontCharInfo('ð', 1046, 1128, 54, 109), 
        new FontCharInfo('ñ', 1110, 1128, 54, 109), 
        new FontCharInfo('ò', 1174, 1128, 54, 109), 
        new FontCharInfo('ó', 1238, 1128, 54, 109), 
        new FontCharInfo('ô', 1302, 1128, 54, 109), 
        new FontCharInfo('õ', 1366, 1128, 54, 109), 
        new FontCharInfo('ö', 10, 1262, 84, 114), 
        new FontCharInfo('÷', 104, 1262, 54, 109), 
        new FontCharInfo('ø', 168, 1262, 54, 109), 
        new FontCharInfo('ù', 232, 1262, 54, 109), 
        new FontCharInfo('ú', 296, 1262, 54, 109), 
        new FontCharInfo('û', 360, 1262, 54, 109), 
        new FontCharInfo('ü', 424, 1262, 54, 109), 
        new FontCharInfo('ý', 488, 1262, 54, 109), 
        new FontCharInfo('þ', 552, 1262, 54, 109), 
        new FontCharInfo('ÿ', 616, 1262, 54, 109), 
        new FontCharInfo('Ā', 680, 1262, 54, 109), 
        new FontCharInfo('ā', 744, 1262, 54, 109), 
        new FontCharInfo('Ă', 808, 1262, 54, 109), 
        new FontCharInfo('ă', 872, 1262, 54, 109), 
        new FontCharInfo('Ą', 936, 1262, 54, 109), 
        new FontCharInfo('ą', 1000, 1262, 54, 109), 
        new FontCharInfo('Ć', 1064, 1262, 54, 109), 
        new FontCharInfo('ć', 1128, 1262, 54, 109), 
        new FontCharInfo('Ĉ', 1192, 1262, 54, 109), 
        new FontCharInfo('ĉ', 1256, 1262, 54, 109), 
        new FontCharInfo('Ċ', 1320, 1262, 54, 109), 
        new FontCharInfo('ċ', 1384, 1262, 54, 109), 
        new FontCharInfo('Č', 10, 1396, 54, 109), 
        new FontCharInfo('č', 74, 1396, 54, 109), 
        new FontCharInfo('Ď', 138, 1396, 54, 109), 
        new FontCharInfo('ď', 202, 1396, 54, 109), 
        new FontCharInfo('Đ', 266, 1396, 54, 109), 
        new FontCharInfo('đ', 330, 1396, 54, 109), 
        new FontCharInfo('Ē', 394, 1396, 54, 109), 
        new FontCharInfo('ē', 458, 1396, 54, 109), 
        new FontCharInfo('Ĕ', 522, 1396, 54, 109), 
        new FontCharInfo('ĕ', 586, 1396, 54, 109), 
        new FontCharInfo('Ė', 650, 1396, 54, 109), 
        new FontCharInfo('ė', 714, 1396, 54, 109), 
        new FontCharInfo('Ę', 778, 1396, 54, 109), 
        new FontCharInfo('ę', 842, 1396, 54, 109), 
        new FontCharInfo('Ě', 906, 1396, 54, 109), 
        new FontCharInfo('ě', 970, 1396, 54, 109), 
        new FontCharInfo('Ĝ', 1034, 1396, 54, 109), 
        new FontCharInfo('ĝ', 1098, 1396, 54, 109), 
        new FontCharInfo('Ğ', 1162, 1396, 54, 109), 
        new FontCharInfo('ğ', 1226, 1396, 54, 109), 
        new FontCharInfo('Ġ', 1290, 1396, 54, 109), 
        new FontCharInfo('ġ', 1354, 1396, 54, 109), 
        new FontCharInfo('Ģ', 1418, 1396, 54, 109), 
        new FontCharInfo('ģ', 10, 1525, 54, 109), 
        new FontCharInfo('Ĥ', 74, 1525, 54, 109), 
        new FontCharInfo('ĥ', 138, 1525, 54, 109), 
        new FontCharInfo('Ħ', 202, 1525, 54, 109), 
        new FontCharInfo('ħ', 266, 1525, 54, 109), 
        new FontCharInfo('Ĩ', 330, 1525, 54, 109), 
        new FontCharInfo('ĩ', 394, 1525, 54, 109), 
        new FontCharInfo('Ī', 458, 1525, 54, 109), 
        new FontCharInfo('ī', 522, 1525, 54, 109), 
        new FontCharInfo('Ĭ', 586, 1525, 54, 109), 
        new FontCharInfo('ĭ', 650, 1525, 54, 109), 
        new FontCharInfo('Į', 714, 1525, 54, 109), 
        new FontCharInfo('į', 778, 1525, 54, 109), 
        new FontCharInfo('İ', 842, 1525, 54, 109), 
        new FontCharInfo('ı', 906, 1525, 54, 109), 
        new FontCharInfo('Ĳ', 970, 1525, 54, 109), 
        new FontCharInfo('ĳ', 1034, 1525, 54, 109), 
        new FontCharInfo('Ĵ', 1098, 1525, 54, 109), 
        new FontCharInfo('ĵ', 1162, 1525, 54, 109), 
        new FontCharInfo('Ķ', 1226, 1525, 54, 109), 
        new FontCharInfo('ķ', 1290, 1525, 54, 109), 
        new FontCharInfo('ĸ', 1354, 1525, 54, 109), 
        new FontCharInfo('Ĺ', 1418, 1525, 54, 109), 
        new FontCharInfo('ĺ', 10, 1654, 54, 109), 
        new FontCharInfo('Ļ', 74, 1654, 54, 109), 
        new FontCharInfo('ļ', 138, 1654, 54, 109), 
        new FontCharInfo('Ľ', 202, 1654, 54, 109), 
        new FontCharInfo('ľ', 266, 1654, 54, 109), 
        new FontCharInfo('Ŀ', 330, 1654, 54, 109), 
        new FontCharInfo('ŀ', 394, 1654, 54, 109), 
        new FontCharInfo('Ł', 458, 1654, 54, 109), 
        new FontCharInfo('ł', 522, 1654, 54, 109), 
        new FontCharInfo('Ń', 586, 1654, 54, 109), 
        new FontCharInfo('ń', 650, 1654, 54, 109), 
        new FontCharInfo('Ņ', 714, 1654, 54, 109), 
        new FontCharInfo('ņ', 778, 1654, 54, 109), 
        new FontCharInfo('Ň', 842, 1654, 54, 109), 
        new FontCharInfo('ň', 906, 1654, 54, 109), 
        new FontCharInfo('ŉ', 970, 1654, 54, 109), 
        new FontCharInfo('Ŋ', 1034, 1654, 54, 109), 
        new FontCharInfo('ŋ', 1098, 1654, 54, 109), 
        new FontCharInfo('Ō', 1162, 1654, 54, 109), 
        new FontCharInfo('ō', 1226, 1654, 54, 109), 
        new FontCharInfo('Ŏ', 1290, 1654, 54, 109), 
        new FontCharInfo('ŏ', 1354, 1654, 54, 109), 
        new FontCharInfo('Ő', 1418, 1654, 54, 109), 
        new FontCharInfo('ő', 10, 1783, 54, 109), 
        new FontCharInfo('Œ', 74, 1783, 54, 109), 
        new FontCharInfo('œ', 138, 1783, 54, 109), 
        new FontCharInfo('Ŕ', 202, 1783, 54, 109), 
        new FontCharInfo('ŕ', 266, 1783, 54, 109), 
        new FontCharInfo('Ŗ', 330, 1783, 54, 109), 
        new FontCharInfo('ŗ', 394, 1783, 54, 109), 
        new FontCharInfo('Ř', 458, 1783, 54, 109), 
        new FontCharInfo('ř', 522, 1783, 54, 109), 
        new FontCharInfo('Ś', 586, 1783, 54, 109), 
        new FontCharInfo('ś', 650, 1783, 54, 109), 
        new FontCharInfo('Ŝ', 714, 1783, 54, 109), 
        new FontCharInfo('ŝ', 778, 1783, 54, 109), 
        new FontCharInfo('Ş', 842, 1783, 54, 109), 
        new FontCharInfo('ş', 906, 1783, 54, 109), 
        new FontCharInfo('Š', 970, 1783, 54, 109), 
        new FontCharInfo('š', 1034, 1783, 54, 109), 
        new FontCharInfo('Ţ', 1098, 1783, 54, 109), 
        new FontCharInfo('ţ', 1162, 1783, 54, 109), 
        new FontCharInfo('Ť', 1226, 1783, 54, 109), 
        new FontCharInfo('ť', 1290, 1783, 54, 109), 
        new FontCharInfo('Ŧ', 1354, 1783, 54, 109), 
        new FontCharInfo('ŧ', 1418, 1783, 54, 109), 
        new FontCharInfo('Ũ', 10, 1912, 54, 109), 
        new FontCharInfo('ũ', 74, 1912, 54, 109), 
        new FontCharInfo('Ū', 138, 1912, 54, 109), 
        new FontCharInfo('ū', 202, 1912, 54, 109), 
        new FontCharInfo('Ŭ', 266, 1912, 54, 109), 
        new FontCharInfo('ŭ', 330, 1912, 54, 109), 
        new FontCharInfo('Ů', 394, 1912, 54, 109), 
        new FontCharInfo('ů', 458, 1912, 54, 109), 
        new FontCharInfo('Ű', 522, 1912, 54, 109), 
        new FontCharInfo('ű', 586, 1912, 54, 109), 
        new FontCharInfo('Ų', 650, 1912, 54, 109), 
        new FontCharInfo('ų', 714, 1912, 54, 109), 
        new FontCharInfo('Ŵ', 778, 1912, 54, 109), 
        new FontCharInfo('ŵ', 842, 1912, 54, 109), 
        new FontCharInfo('Ŷ', 906, 1912, 54, 109), 
        new FontCharInfo('ŷ', 970, 1912, 54, 109), 
        new FontCharInfo('Ÿ', 1034, 1912, 54, 109), 
        new FontCharInfo('Ź', 1098, 1912, 54, 109), 
        new FontCharInfo('ź', 1162, 1912, 54, 109), 
        new FontCharInfo('Ż', 1226, 1912, 54, 109), 
        new FontCharInfo('ż', 1290, 1912, 54, 109), 
        new FontCharInfo('Ž', 1354, 1912, 54, 109), 
        new FontCharInfo('ž', 1418, 1912, 54, 109), 
        new FontCharInfo('ſ', 10, 2041, 54, 109), 
    }; 
    public static FontCharInfo[] getCharInfo() { 
        return InmyheadSpriteFont.letters; 
    } 
} 

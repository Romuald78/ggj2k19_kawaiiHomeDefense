package fr.rphstudio.ecs.component.render.font; 
public class CuteSpriteFont { 
    private static FontCharInfo[] letters = { 
        new FontCharInfo(' ', 10, 0, 48, 127), 
        new FontCharInfo('!', 68, 0, 42, 134), 
        new FontCharInfo('"', 120, 0, 67, 127), 
        new FontCharInfo('#', 197, 0, 120, 135), 
        new FontCharInfo('$', 327, 0, 95, 157), 
        new FontCharInfo('%', 432, 0, 98, 130), 
        new FontCharInfo('&', 540, 0, 111, 129), 
        new FontCharInfo('\'', 661, 0, 31, 127), 
        new FontCharInfo('(', 702, 0, 62, 130), 
        new FontCharInfo(')', 774, 0, 60, 128), 
        new FontCharInfo('*', 844, 0, 89, 127), 
        new FontCharInfo('+', 943, 0, 104, 127), 
        new FontCharInfo(',', 1057, 0, 35, 133), 
        new FontCharInfo('-', 1102, 0, 91, 127), 
        new FontCharInfo('.', 1203, 0, 34, 127), 
        new FontCharInfo('/', 1247, 0, 75, 128), 
        new FontCharInfo('0', 1332, 0, 104, 129), 
        new FontCharInfo('1', 1446, 0, 53, 130), 
        new FontCharInfo('2', 10, 177, 101, 128), 
        new FontCharInfo('3', 121, 177, 91, 128), 
        new FontCharInfo('4', 222, 177, 107, 130), 
        new FontCharInfo('5', 339, 177, 87, 128), 
        new FontCharInfo('6', 436, 177, 94, 128), 
        new FontCharInfo('7', 540, 177, 96, 128), 
        new FontCharInfo('8', 646, 177, 87, 130), 
        new FontCharInfo('9', 743, 177, 84, 131), 
        new FontCharInfo(':', 837, 177, 33, 127), 
        new FontCharInfo(';', 880, 177, 37, 135), 
        new FontCharInfo('<', 927, 177, 63, 127), 
        new FontCharInfo('=', 1000, 177, 72, 127), 
        new FontCharInfo('>', 1082, 177, 69, 127), 
        new FontCharInfo('?', 1161, 177, 93, 134), 
        new FontCharInfo('@', 1264, 177, 118, 127), 
        new FontCharInfo('A', 1392, 177, 107, 133), 
        new FontCharInfo('B', 10, 332, 101, 133), 
        new FontCharInfo('C', 121, 332, 113, 129), 
        new FontCharInfo('D', 244, 332, 96, 131), 
        new FontCharInfo('E', 350, 332, 86, 135), 
        new FontCharInfo('F', 446, 332, 86, 135), 
        new FontCharInfo('G', 542, 332, 103, 134), 
        new FontCharInfo('H', 655, 332, 97, 131), 
        new FontCharInfo('I', 762, 332, 34, 130), 
        new FontCharInfo('J', 806, 332, 80, 132), 
        new FontCharInfo('K', 896, 332, 96, 134), 
        new FontCharInfo('L', 1002, 332, 72, 131), 
        new FontCharInfo('M', 1084, 332, 113, 134), 
        new FontCharInfo('N', 1207, 332, 99, 132), 
        new FontCharInfo('O', 1316, 332, 107, 128), 
        new FontCharInfo('P', 10, 487, 88, 132), 
        new FontCharInfo('Q', 108, 487, 108, 136), 
        new FontCharInfo('R', 226, 487, 88, 133), 
        new FontCharInfo('S', 324, 487, 95, 137), 
        new FontCharInfo('T', 429, 487, 92, 131), 
        new FontCharInfo('U', 531, 487, 90, 129), 
        new FontCharInfo('V', 631, 487, 100, 129), 
        new FontCharInfo('W', 741, 487, 112, 128), 
        new FontCharInfo('X', 863, 487, 104, 131), 
        new FontCharInfo('Y', 977, 487, 99, 131), 
        new FontCharInfo('Z', 1086, 487, 105, 128), 
        new FontCharInfo('[', 1201, 487, 75, 133), 
        new FontCharInfo('\\', 1286, 487, 68, 132), 
        new FontCharInfo(']', 1364, 487, 71, 129), 
        new FontCharInfo('^', 10, 644, 69, 127), 
        new FontCharInfo('_', 89, 644, 108, 129), 
        new FontCharInfo('`', 207, 644, 120, 127), 
        new FontCharInfo('a', 337, 644, 107, 133), 
        new FontCharInfo('b', 454, 644, 101, 133), 
        new FontCharInfo('c', 565, 644, 113, 129), 
        new FontCharInfo('d', 688, 644, 96, 131), 
        new FontCharInfo('e', 794, 644, 86, 135), 
        new FontCharInfo('f', 890, 644, 86, 135), 
        new FontCharInfo('g', 986, 644, 103, 134), 
        new FontCharInfo('h', 1099, 644, 97, 131), 
        new FontCharInfo('i', 1206, 644, 34, 130), 
        new FontCharInfo('j', 1250, 644, 80, 132), 
        new FontCharInfo('k', 1340, 644, 96, 134), 
        new FontCharInfo('l', 10, 799, 72, 131), 
        new FontCharInfo('m', 92, 799, 113, 134), 
        new FontCharInfo('n', 215, 799, 99, 132), 
        new FontCharInfo('o', 324, 799, 107, 128), 
        new FontCharInfo('p', 441, 799, 88, 132), 
        new FontCharInfo('q', 539, 799, 108, 136), 
        new FontCharInfo('r', 657, 799, 88, 133), 
        new FontCharInfo('s', 755, 799, 95, 137), 
        new FontCharInfo('t', 860, 799, 92, 131), 
        new FontCharInfo('u', 962, 799, 90, 129), 
        new FontCharInfo('v', 1062, 799, 100, 129), 
        new FontCharInfo('w', 1172, 799, 112, 128), 
        new FontCharInfo('x', 1294, 799, 104, 131), 
        new FontCharInfo('y', 10, 956, 99, 131), 
        new FontCharInfo('z', 119, 956, 105, 128), 
        new FontCharInfo('{', 234, 956, 80, 127), 
        new FontCharInfo('|', 324, 956, 33, 130), 
        new FontCharInfo('}', 367, 956, 80, 129), 
        new FontCharInfo('~', 457, 956, 100, 127), 
        new FontCharInfo('¡', 567, 956, 120, 127), 
        new FontCharInfo('¢', 697, 956, 73, 127), 
        new FontCharInfo('£', 780, 956, 90, 130), 
        new FontCharInfo('¤', 880, 956, 120, 127), 
        new FontCharInfo('¥', 1010, 956, 120, 127), 
        new FontCharInfo('¦', 1140, 956, 120, 127), 
        new FontCharInfo('§', 1270, 956, 120, 127), 
        new FontCharInfo('¨', 10, 1107, 120, 127), 
        new FontCharInfo('©', 140, 1107, 120, 127), 
        new FontCharInfo('ª', 270, 1107, 120, 127), 
        new FontCharInfo('«', 400, 1107, 120, 127), 
        new FontCharInfo('¬', 530, 1107, 120, 127), 
        new FontCharInfo('­', 660, 1107, 120, 127), 
        new FontCharInfo('®', 790, 1107, 120, 127), 
        new FontCharInfo('¯', 920, 1107, 120, 127), 
        new FontCharInfo('°', 1050, 1107, 120, 127), 
        new FontCharInfo('±', 1180, 1107, 120, 127), 
        new FontCharInfo('²', 1310, 1107, 120, 127), 
        new FontCharInfo('³', 10, 1254, 120, 127), 
        new FontCharInfo('´', 140, 1254, 120, 127), 
        new FontCharInfo('µ', 270, 1254, 120, 127), 
        new FontCharInfo('¶', 400, 1254, 120, 127), 
        new FontCharInfo('·', 530, 1254, 120, 127), 
        new FontCharInfo('¸', 660, 1254, 120, 127), 
        new FontCharInfo('¹', 790, 1254, 120, 127), 
        new FontCharInfo('º', 920, 1254, 120, 127), 
        new FontCharInfo('»', 1050, 1254, 120, 127), 
        new FontCharInfo('¼', 1180, 1254, 120, 127), 
        new FontCharInfo('½', 1310, 1254, 120, 127), 
        new FontCharInfo('¾', 10, 1401, 120, 127), 
        new FontCharInfo('¿', 140, 1401, 120, 127), 
        new FontCharInfo('À', 270, 1401, 120, 127), 
        new FontCharInfo('Á', 400, 1401, 120, 127), 
        new FontCharInfo('Â', 530, 1401, 120, 127), 
        new FontCharInfo('Ã', 660, 1401, 120, 127), 
        new FontCharInfo('Ä', 790, 1401, 120, 127), 
        new FontCharInfo('Å', 920, 1401, 120, 127), 
        new FontCharInfo('Æ', 1050, 1401, 120, 127), 
        new FontCharInfo('Ç', 1180, 1401, 120, 127), 
        new FontCharInfo('È', 1310, 1401, 120, 127), 
        new FontCharInfo('É', 10, 1548, 120, 127), 
        new FontCharInfo('Ê', 140, 1548, 120, 127), 
        new FontCharInfo('Ë', 270, 1548, 120, 127), 
        new FontCharInfo('Ì', 400, 1548, 120, 127), 
        new FontCharInfo('Í', 530, 1548, 120, 127), 
        new FontCharInfo('Î', 660, 1548, 120, 127), 
        new FontCharInfo('Ï', 790, 1548, 120, 127), 
        new FontCharInfo('Ð', 920, 1548, 120, 127), 
        new FontCharInfo('Ñ', 1050, 1548, 120, 127), 
        new FontCharInfo('Ò', 1180, 1548, 120, 127), 
        new FontCharInfo('Ó', 1310, 1548, 120, 127), 
        new FontCharInfo('Ô', 10, 1695, 120, 127), 
        new FontCharInfo('Õ', 140, 1695, 120, 127), 
        new FontCharInfo('Ö', 270, 1695, 120, 127), 
        new FontCharInfo('×', 400, 1695, 120, 127), 
        new FontCharInfo('Ø', 530, 1695, 120, 127), 
        new FontCharInfo('Ù', 660, 1695, 120, 127), 
        new FontCharInfo('Ú', 790, 1695, 120, 127), 
        new FontCharInfo('Û', 920, 1695, 120, 127), 
        new FontCharInfo('Ü', 1050, 1695, 120, 127), 
        new FontCharInfo('Ý', 1180, 1695, 120, 127), 
        new FontCharInfo('Þ', 1310, 1695, 120, 127), 
        new FontCharInfo('ß', 10, 1842, 120, 127), 
        new FontCharInfo('à', 140, 1842, 120, 127), 
        new FontCharInfo('á', 270, 1842, 120, 127), 
        new FontCharInfo('â', 400, 1842, 120, 127), 
        new FontCharInfo('ã', 530, 1842, 120, 127), 
        new FontCharInfo('ä', 660, 1842, 120, 127), 
        new FontCharInfo('å', 790, 1842, 120, 127), 
        new FontCharInfo('æ', 920, 1842, 120, 127), 
        new FontCharInfo('ç', 1050, 1842, 120, 127), 
        new FontCharInfo('è', 1180, 1842, 120, 127), 
        new FontCharInfo('é', 1310, 1842, 120, 127), 
        new FontCharInfo('ê', 10, 1989, 120, 127), 
        new FontCharInfo('ë', 140, 1989, 120, 127), 
        new FontCharInfo('ì', 270, 1989, 120, 127), 
        new FontCharInfo('í', 400, 1989, 120, 127), 
        new FontCharInfo('î', 530, 1989, 120, 127), 
        new FontCharInfo('ï', 660, 1989, 120, 127), 
        new FontCharInfo('ð', 790, 1989, 120, 127), 
        new FontCharInfo('ñ', 920, 1989, 120, 127), 
        new FontCharInfo('ò', 1050, 1989, 120, 127), 
        new FontCharInfo('ó', 1180, 1989, 120, 127), 
        new FontCharInfo('ô', 1310, 1989, 120, 127), 
        new FontCharInfo('õ', 10, 2136, 120, 127), 
        new FontCharInfo('ö', 140, 2136, 120, 127), 
        new FontCharInfo('÷', 270, 2136, 120, 127), 
        new FontCharInfo('ø', 400, 2136, 120, 127), 
        new FontCharInfo('ù', 530, 2136, 120, 127), 
        new FontCharInfo('ú', 660, 2136, 120, 127), 
        new FontCharInfo('û', 790, 2136, 120, 127), 
        new FontCharInfo('ü', 920, 2136, 120, 127), 
        new FontCharInfo('ý', 1050, 2136, 120, 127), 
        new FontCharInfo('þ', 1180, 2136, 120, 127), 
        new FontCharInfo('ÿ', 1310, 2136, 120, 127), 
        new FontCharInfo('Ā', 10, 2283, 120, 127), 
        new FontCharInfo('ā', 140, 2283, 120, 127), 
        new FontCharInfo('Ă', 270, 2283, 120, 127), 
        new FontCharInfo('ă', 400, 2283, 120, 127), 
        new FontCharInfo('Ą', 530, 2283, 120, 127), 
        new FontCharInfo('ą', 660, 2283, 120, 127), 
        new FontCharInfo('Ć', 790, 2283, 120, 127), 
        new FontCharInfo('ć', 920, 2283, 120, 127), 
        new FontCharInfo('Ĉ', 1050, 2283, 120, 127), 
        new FontCharInfo('ĉ', 1180, 2283, 120, 127), 
        new FontCharInfo('Ċ', 1310, 2283, 120, 127), 
        new FontCharInfo('ċ', 10, 2430, 120, 127), 
        new FontCharInfo('Č', 140, 2430, 120, 127), 
        new FontCharInfo('č', 270, 2430, 120, 127), 
        new FontCharInfo('Ď', 400, 2430, 120, 127), 
        new FontCharInfo('ď', 530, 2430, 120, 127), 
        new FontCharInfo('Đ', 660, 2430, 120, 127), 
        new FontCharInfo('đ', 790, 2430, 120, 127), 
        new FontCharInfo('Ē', 920, 2430, 120, 127), 
        new FontCharInfo('ē', 1050, 2430, 120, 127), 
        new FontCharInfo('Ĕ', 1180, 2430, 120, 127), 
        new FontCharInfo('ĕ', 1310, 2430, 120, 127), 
        new FontCharInfo('Ė', 10, 2577, 120, 127), 
        new FontCharInfo('ė', 140, 2577, 120, 127), 
        new FontCharInfo('Ę', 270, 2577, 120, 127), 
        new FontCharInfo('ę', 400, 2577, 120, 127), 
        new FontCharInfo('Ě', 530, 2577, 120, 127), 
        new FontCharInfo('ě', 660, 2577, 120, 127), 
        new FontCharInfo('Ĝ', 790, 2577, 120, 127), 
        new FontCharInfo('ĝ', 920, 2577, 120, 127), 
        new FontCharInfo('Ğ', 1050, 2577, 120, 127), 
        new FontCharInfo('ğ', 1180, 2577, 120, 127), 
        new FontCharInfo('Ġ', 1310, 2577, 120, 127), 
        new FontCharInfo('ġ', 10, 2724, 120, 127), 
        new FontCharInfo('Ģ', 140, 2724, 120, 127), 
        new FontCharInfo('ģ', 270, 2724, 120, 127), 
        new FontCharInfo('Ĥ', 400, 2724, 120, 127), 
        new FontCharInfo('ĥ', 530, 2724, 120, 127), 
        new FontCharInfo('Ħ', 660, 2724, 120, 127), 
        new FontCharInfo('ħ', 790, 2724, 120, 127), 
        new FontCharInfo('Ĩ', 920, 2724, 120, 127), 
        new FontCharInfo('ĩ', 1050, 2724, 120, 127), 
        new FontCharInfo('Ī', 1180, 2724, 120, 127), 
        new FontCharInfo('ī', 1310, 2724, 120, 127), 
        new FontCharInfo('Ĭ', 10, 2871, 120, 127), 
        new FontCharInfo('ĭ', 140, 2871, 120, 127), 
        new FontCharInfo('Į', 270, 2871, 120, 127), 
        new FontCharInfo('į', 400, 2871, 120, 127), 
        new FontCharInfo('İ', 530, 2871, 120, 127), 
        new FontCharInfo('ı', 660, 2871, 120, 127), 
        new FontCharInfo('Ĳ', 790, 2871, 120, 127), 
        new FontCharInfo('ĳ', 920, 2871, 120, 127), 
        new FontCharInfo('Ĵ', 1050, 2871, 120, 127), 
        new FontCharInfo('ĵ', 1180, 2871, 120, 127), 
        new FontCharInfo('Ķ', 1310, 2871, 120, 127), 
        new FontCharInfo('ķ', 10, 3018, 120, 127), 
        new FontCharInfo('ĸ', 140, 3018, 120, 127), 
        new FontCharInfo('Ĺ', 270, 3018, 120, 127), 
        new FontCharInfo('ĺ', 400, 3018, 120, 127), 
        new FontCharInfo('Ļ', 530, 3018, 120, 127), 
        new FontCharInfo('ļ', 660, 3018, 120, 127), 
        new FontCharInfo('Ľ', 790, 3018, 120, 127), 
        new FontCharInfo('ľ', 920, 3018, 120, 127), 
        new FontCharInfo('Ŀ', 1050, 3018, 120, 127), 
        new FontCharInfo('ŀ', 1180, 3018, 120, 127), 
        new FontCharInfo('Ł', 1310, 3018, 120, 127), 
        new FontCharInfo('ł', 10, 3165, 120, 127), 
        new FontCharInfo('Ń', 140, 3165, 120, 127), 
        new FontCharInfo('ń', 270, 3165, 120, 127), 
        new FontCharInfo('Ņ', 400, 3165, 120, 127), 
        new FontCharInfo('ņ', 530, 3165, 120, 127), 
        new FontCharInfo('Ň', 660, 3165, 120, 127), 
        new FontCharInfo('ň', 790, 3165, 120, 127), 
        new FontCharInfo('ŉ', 920, 3165, 120, 127), 
        new FontCharInfo('Ŋ', 1050, 3165, 120, 127), 
        new FontCharInfo('ŋ', 1180, 3165, 120, 127), 
        new FontCharInfo('Ō', 1310, 3165, 120, 127), 
        new FontCharInfo('ō', 10, 3312, 120, 127), 
        new FontCharInfo('Ŏ', 140, 3312, 120, 127), 
        new FontCharInfo('ŏ', 270, 3312, 120, 127), 
        new FontCharInfo('Ő', 400, 3312, 120, 127), 
        new FontCharInfo('ő', 530, 3312, 120, 127), 
        new FontCharInfo('Œ', 660, 3312, 120, 127), 
        new FontCharInfo('œ', 790, 3312, 120, 127), 
        new FontCharInfo('Ŕ', 920, 3312, 120, 127), 
        new FontCharInfo('ŕ', 1050, 3312, 120, 127), 
        new FontCharInfo('Ŗ', 1180, 3312, 120, 127), 
        new FontCharInfo('ŗ', 1310, 3312, 120, 127), 
        new FontCharInfo('Ř', 10, 3459, 120, 127), 
        new FontCharInfo('ř', 140, 3459, 120, 127), 
        new FontCharInfo('Ś', 270, 3459, 120, 127), 
        new FontCharInfo('ś', 400, 3459, 120, 127), 
        new FontCharInfo('Ŝ', 530, 3459, 120, 127), 
        new FontCharInfo('ŝ', 660, 3459, 120, 127), 
        new FontCharInfo('Ş', 790, 3459, 120, 127), 
        new FontCharInfo('ş', 920, 3459, 120, 127), 
        new FontCharInfo('Š', 1050, 3459, 120, 127), 
        new FontCharInfo('š', 1180, 3459, 120, 127), 
        new FontCharInfo('Ţ', 1310, 3459, 120, 127), 
        new FontCharInfo('ţ', 10, 3606, 120, 127), 
        new FontCharInfo('Ť', 140, 3606, 120, 127), 
        new FontCharInfo('ť', 270, 3606, 120, 127), 
        new FontCharInfo('Ŧ', 400, 3606, 120, 127), 
        new FontCharInfo('ŧ', 530, 3606, 120, 127), 
        new FontCharInfo('Ũ', 660, 3606, 120, 127), 
        new FontCharInfo('ũ', 790, 3606, 120, 127), 
        new FontCharInfo('Ū', 920, 3606, 120, 127), 
        new FontCharInfo('ū', 1050, 3606, 120, 127), 
        new FontCharInfo('Ŭ', 1180, 3606, 120, 127), 
        new FontCharInfo('ŭ', 1310, 3606, 120, 127), 
        new FontCharInfo('Ů', 10, 3753, 120, 127), 
        new FontCharInfo('ů', 140, 3753, 120, 127), 
        new FontCharInfo('Ű', 270, 3753, 120, 127), 
        new FontCharInfo('ű', 400, 3753, 120, 127), 
        new FontCharInfo('Ų', 530, 3753, 120, 127), 
        new FontCharInfo('ų', 660, 3753, 120, 127), 
        new FontCharInfo('Ŵ', 790, 3753, 120, 127), 
        new FontCharInfo('ŵ', 920, 3753, 120, 127), 
        new FontCharInfo('Ŷ', 1050, 3753, 120, 127), 
        new FontCharInfo('ŷ', 1180, 3753, 120, 127), 
        new FontCharInfo('Ÿ', 1310, 3753, 120, 127), 
        new FontCharInfo('Ź', 10, 3900, 120, 127), 
        new FontCharInfo('ź', 140, 3900, 120, 127), 
        new FontCharInfo('Ż', 270, 3900, 120, 127), 
        new FontCharInfo('ż', 400, 3900, 120, 127), 
        new FontCharInfo('Ž', 530, 3900, 120, 127), 
        new FontCharInfo('ž', 660, 3900, 120, 127), 
        new FontCharInfo('ſ', 790, 3900, 120, 127), 
    }; 
    public static FontCharInfo[] getCharInfo() { 
        return CuteSpriteFont.letters; 
    } 
} 

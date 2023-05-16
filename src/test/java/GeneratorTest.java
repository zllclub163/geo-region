import com.ww.geo.region.generator.Region2GeoHashUtil;
import org.junit.Test;

import java.util.List;

/**
 * @author Zhanglele
 * @version 2023/5/15
 */
public class GeneratorTest {

    @Test
    public void generatorGeoStr() {
        List<String> strings = Region2GeoHashUtil.regionSetting2GeoStr("120.7296 30.7488,122.0079 30.8752,121.8756 31.2030,120.8326 31.9219,120.5796 31.2926,120.7296 30.7488,120.7296 30.7488,120.7296 30.7488", 5);
        System.out.println();
    }
}

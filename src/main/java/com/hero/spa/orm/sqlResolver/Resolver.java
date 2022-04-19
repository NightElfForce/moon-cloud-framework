package com.hero.spa.orm.sqlResolver;

import lombok.Data;
import java.util.List;
/**
 * @author yinx
 */
@Data
public class Resolver {

    private String sql;

    private List<String> param;


}

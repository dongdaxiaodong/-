package com.bladejava.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.blade.Blade;
import com.blade.ioc.annotation.Bean;
import com.blade.loader.BladeLoader;
import com.blade.mvc.view.template.JetbrickTemplateEngine;
import io.github.biezhi.anima.Anima;

@Bean
public class StartUpConfig implements BladeLoader {


    @Override
    public void load(Blade blade){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl(blade.environment().getOrNull("jdbc.url"));
        dataSource.setUsername(blade.environment().getOrNull("jdbc.username"));
        dataSource.setPassword(blade.environment().getOrNull("jdbc.password"));
        Anima.open(dataSource);

    }
}
package com.example.core.config.db;

import org.springframework.context.annotation.Import;

@Import({
        EmbeddedDBConfiguration.class,
        DbConfiguration.class
})
class DaoConfiguration {
}

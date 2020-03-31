package com.ntthuat.auth.repository;

import com.ntthuat.common.config.FakerPOJOFactoryConfiguration;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import uk.co.jemos.podam.api.PodamFactory;

import javax.inject.Inject;

/**
 * @author ntthuat
 */
@DataJpaTest
@Import({FakerPOJOFactoryConfiguration.class})
@ExtendWith(SpringExtension.class)
abstract class RepositoryTestBase {

    @Inject
    PodamFactory podamFactory;
}

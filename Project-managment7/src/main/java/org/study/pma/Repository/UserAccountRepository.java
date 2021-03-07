package org.study.pma.Repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.study.pma.Entities.UserAccount;


public interface UserAccountRepository  extends PagingAndSortingRepository<UserAccount, Long>{

}

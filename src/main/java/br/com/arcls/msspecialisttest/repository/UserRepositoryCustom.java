package br.com.arcls.msspecialisttest.repository;

import br.com.arcls.msspecialisttest.domain.dto.Page;
import br.com.arcls.msspecialisttest.domain.dto.SearchUsersQuery;
import br.com.arcls.msspecialisttest.domain.model.User;

import java.util.List;

public interface UserRepositoryCustom {
    List<User> searchUsers(Page page, SearchUsersQuery query);
}

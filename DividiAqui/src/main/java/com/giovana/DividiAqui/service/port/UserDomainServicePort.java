package com.giovana.DividiAqui.service.port;

import com.giovana.DividiAqui.model.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

/**
 * Interface para o serviço responsável pela gestão de usuários, incluindo criação, recuperação, atualização e exclusão.
 */
public interface UserDomainServicePort {

    /**
     * Cria um novo usuário.
     *
     * @param user O usuário a ser criado.
     * @return O usuário criado.
     */
    User createUser(User user);

    /**
     * Recupera todos os usuários.
     *
     * @return Lista de todos os usuários.
     */
    List<User> getAllUsers();

    /**
     * Recupera um usuário pelo seu ID.
     *
     * @param idUser Identificador do usuário.
     * @return Um Optional contendo o usuário se encontrado, ou vazio se não encontrado.
     */
    Optional<User> getUserById(UUID idUser);

    /**
     * Recupera um usuário pelo nome.
     *
     * @param name O nome do usuário.
     * @return Um Optional contendo o usuário se encontrado, ou vazio se não encontrado.
     */
    Optional<User> getUserByName(String name);

    /**
     * Atualiza um usuário existente.
     *
     * @param user O usuário a ser atualizado.
     * @return O usuário atualizado.
     */
    User updateUser(User user);

    /**
     * Recupera um usuário pelo seu email.
     *
     * @param email O email do usuário a ser recuperado.
     * @return Um Optional contendo o usuário se encontrado, ou vazio se não encontrado.
     */
    Optional<User> getUserByEmail(String email);

    /**
     * Exclui um usuário pelo seu ID. A exclusão é feita atualizando o status do usuário.
     *
     * @param idUser Identificador do usuário a ser excluído.
     */
    void deleteUserById(UUID idUser);
}
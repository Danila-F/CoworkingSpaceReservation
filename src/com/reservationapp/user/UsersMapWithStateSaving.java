package com.reservationapp.user;

class UsersMapWithStateSaving extends UsersMapInMemory {

    public UsersMapWithStateSaving() {
        UsersRepository usersRepository = new UsersRepository(this);
        usersRepository.load();
        Runtime.getRuntime().addShutdownHook(new Thread(usersRepository::save));
    }
}

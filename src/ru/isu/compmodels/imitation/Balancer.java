package ru.isu.compmodels.imitation;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public interface Balancer {
    /**
     * Позволяет задать пул серверов для балансировщика нагрузки
     * @param servers - пул серверов
     */
    void setServerPool(ArrayList<Server> servers);

    /**
     * Возвращает пул серверов балансировщика
     * @return коллекцию серверов, используемых балансировщиком
     */
    ArrayList<Server> getServerPool();

    /**
     * Балансировка. Балансировщик должен выбрать сервер, который будет обрабатывать новый входящий запрос
     * @param request
     * @return
     */
    Server balance(Request request);

    /**
     * Обработка входящего запроса. Предварительно решается задача балансировки, выбора сервера. Затем выбранному серверу
     * передается запрос для обработки.
     * @param request
     */
    void process(Request request);

    void getServerStatus();

}

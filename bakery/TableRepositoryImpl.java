package bakery;

import bakery.entities.tables.interfaces.Table;
import bakery.repositories.interfaces.TableRepository;

import java.util.ArrayList;
import java.util.Collection;

public class TableRepositoryImpl implements TableRepository<Table> {

    Collection<Table> tables;

    public TableRepositoryImpl() {
        tables = new ArrayList<>();
    }

    @Override
    public Collection<Table> getAll() {
        return tables;
    }

    @Override
    public void add(Table table) {
        tables.add(table);
    }

    @Override
    public Table getByNumber(int number) {
        return tables.stream().filter(table -> table.getTableNumber() == number).filter(table -> table.isReserved()).findFirst().orElse(null);
    }
}

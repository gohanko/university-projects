import { DataTypes, Sequelize } from "sequelize";

const event = (sequelize: Sequelize) => {
    const Event = sequelize.define(
        'event',
        {
            id: {
                type: DataTypes.INTEGER,
                primaryKey: true,
                autoIncrement: true,
            },
            name: {
                type: DataTypes.STRING,
            },
            description: {
                type: DataTypes.STRING
            },
            startDate: {
                type: DataTypes.DATE
            },
            endDate: {
                type: DataTypes.DATE
            },
        }
    )

    Event.hasMany(Event, {
        foreignKey: {
            name: "parentEventId",
            allowNull: true,
        }
    })
    Event.belongsTo(Event, {
        foreignKey: {
        name: "parentEventId",
        allowNull: false,
        }
    })

    return Event
}

export default event
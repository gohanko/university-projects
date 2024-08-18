import { DataTypes, Sequelize } from "sequelize";
import user from "./user.model";

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
            code: {
                type: DataTypes.UUID,
                defaultValue: DataTypes.UUIDV4,
            }
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

    Event.belongsTo(user(sequelize), {
        foreignKey: {
            name: "createdByUserId",
        }
    })

    return Event
}

export default event
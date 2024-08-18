import sequelize, { DataTypes, Sequelize } from "sequelize"
import user from "./user.model"
import event from "./event.model"

const eventParticipation = (sequelize: Sequelize) => {
    const EventParticipation = sequelize.define(
        'eventParticipation',
        {
            id: {
                type: DataTypes.INTEGER,
                primaryKey: true,
                autoIncrement: true,
            },
            isAttended: {
                type: DataTypes.BOOLEAN,
                defaultValue: false,
            }
        }
    )

    EventParticipation.belongsTo(user(sequelize), {
        foreignKey: {
            name: "participatedByUserId",
        }
    })
    EventParticipation.belongsTo(event(sequelize), {
        foreignKey: {
            name: "eventId",
        }
    })
    
    return EventParticipation
}

export default eventParticipation
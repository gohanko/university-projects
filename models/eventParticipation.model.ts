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
        }
    )

    EventParticipation.belongsTo(user(sequelize))
    EventParticipation.belongsTo(event(sequelize))
    
    return EventParticipation
}

export default eventParticipation
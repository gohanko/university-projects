import databaseService from './services/database.service';
import expressApp from './espressApp'
import { API_PORT } from './configs/'

databaseService.sequelize.sync()
    .then(() => {
        console.log("[server] : Synced database");
        expressApp.listen(
            API_PORT,
            () => console.log(`[server] : Server is running on http://localhost:${API_PORT}`)
        )
    })
    .catch((err) => {
        console.log("[server] : Failed to sync database: " + err.message);
    });

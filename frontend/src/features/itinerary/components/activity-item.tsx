import type { ActivityResponse } from "../types/itinerary.types";

const ActivityItem = ({ activity }: { activity: ActivityResponse }) => {
    return (
        <div className="border p-3 rounded shadow-sm flex justify-between items-start">
            <div>
                <p className="font-semibold">{activity.name}</p>
                <p className="text-sm text-gray-500">{activity.time}</p>
                {activity.location && <p className="text-sm">{activity.location}</p>}
                {activity.description && <p className="text-sm">{activity.description}</p>}
                {activity.category && <p className="text-sm">{activity.category}</p>}
                {activity.notes && <p className="text-sm italic">{activity.notes}</p>}
            </div>
        </div>
    );
}

export default ActivityItem;
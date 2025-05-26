import ActivityItem from "./activity-item";
import type { DayPlanResponse } from "../types/itinerary.types";

const DayPlan = ({ day }: { day: DayPlanResponse }) => {
    return (
        <div className="space-y-4">
            {day.activities.map((activity, idx) => (
                <ActivityItem key={idx} activity={activity} />
            ))}
        </div>
    );
}

export default DayPlan;
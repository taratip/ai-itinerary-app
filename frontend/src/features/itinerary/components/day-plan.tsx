import ActivityItem from "./activity-item";
import type { DayPlanResponse } from "../types/itinerary.types";

const DayPlan = ({ day, dayNumber }: { day: DayPlanResponse; dayNumber: number }) => {
  return (
    <div className="space-y-4">
      <div className="bg-white border border-gray-200 rounded-2xl shadow-sm px-4 py-3">
        <p className="text-sm text-gray-500">Day {dayNumber}</p>
        <p className="text-lg font-semibold text-gray-900">{day.date}</p>
      </div>

      <div className="space-y-4">
        {day.activities.map((activity, idx) => (
          <ActivityItem key={activity.id ?? idx} activity={activity} />
        ))}
      </div>
    </div>
  );
};

export default DayPlan;

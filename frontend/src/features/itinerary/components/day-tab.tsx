import { useState } from "react";
import DayPlan from "./day-plan";
import type { DayPlanResponse } from "../types/itinerary.types";

const DayTab = ({ days }: { days: DayPlanResponse[] }) => {
  const [activeIndex, setActiveIndex] = useState(0);

  return (
    <div>
      <div className="flex gap-2 overflow-x-auto pb-2 border-b border-gray-200 mb-4">
        {days.map((_, idx) => {
          const active = idx === activeIndex;
          return (
            <button
              key={idx}
              onClick={() => setActiveIndex(idx)}
              className={[
                "px-4 py-2 rounded-full text-sm font-medium whitespace-nowrap transition",
                active
                  ? "bg-blue-600 text-white shadow"
                  : "bg-white text-gray-600 border border-gray-200 hover:bg-gray-50",
              ].join(" ")}
            >
              Day {idx + 1}
            </button>
          );
        })}
      </div>

      <DayPlan day={days[activeIndex]} dayNumber={activeIndex + 1} />
    </div>
  );
};

export default DayTab;

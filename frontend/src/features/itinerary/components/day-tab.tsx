import { useState } from "react";
import DayPlan from "./day-plan";
import type { DayPlanResponse } from "../types/itinerary.types";

const DayTab = ({ days }: { days: DayPlanResponse[] }) => {
    const [activeIndex, setActiveIndex] = useState(0);

    return (
        <div>
            <div className="flex gap-2 border-b mb-4">
                {days.map((_, idx) => (
                    <button
                        key={idx}
                        onClick={() => setActiveIndex(idx)}
                        className={`px-4 py-2 ${idx === activeIndex ? "border-b-2 border-blue-600" : "text-gray-500"}`}
                    >
                        Day {idx + 1}
                    </button>
                ))}
            </div>
            <DayPlan day={days[activeIndex]} />
        </div>
    );
}

export default DayTab;
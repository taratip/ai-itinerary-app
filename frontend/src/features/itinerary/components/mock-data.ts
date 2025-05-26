import type { DayPlanResponse } from "../types/itinerary.types";

export const mockDayPlans: DayPlanResponse[] = [
  {
    id: "1",
    date: "2025-06-10T00:00:00Z",
    activities: [
      {
        id: "1",
        name: "Visit Eiffel Tower",
        time: "09:00",
        location: "Paris, France",
        notes: "Buy tickets in advance",
        category: "sightseeing",
      },
      {
        id: "2",
        name: "Lunch at Le Bistro",
        time: "12:30",
        location: "Paris, France",
        notes: "Outdoor patio",
        category: "food",
      },
    ],
  },
  {
    id: "2",
    date: "2025-06-11T00:00:00Z",
    activities: [
      {
        id: "3",
        name: "Louvre Museum",
        time: "10:00",
        location: "Paris, France",
        notes: "Start early",
        category: "sightseeing",
      },
    ],
  },
];

export interface CreateItineraryRequest {
    title: string;
    destination: string;
    startDate: string;
    endDate: string;
    travelers?: string[];
    dayPlans?: DayPlanRequest[];
    notes?: string;
    budget?: string;
    interests?: string[];
}

export interface DayPlanRequest {
    date: string;
    activities: ActivityRequest[];
}

export interface ActivityRequest {
    name: string;
    time: string;
    location: string;
    description?: string;
    category?: "food" | "sightseeing" | "activity" | "transportation" | "other";
    notes?: string;
}

export interface ActivityResponse extends ActivityRequest {
    id: string;
}

export interface DayPlanResponse extends DayPlanRequest {
    id: string;
    activities: ActivityResponse[];
}

export interface ItineraryResponse extends CreateItineraryRequest {
    id: string;
    dayPlans: DayPlanResponse[];
    createdAt: string;
    updatedAt: string;
}
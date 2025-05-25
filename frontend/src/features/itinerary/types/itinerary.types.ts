export interface CreateItineraryRequest {
    title: string;
    destination: string;
    startDate: string;
    endDate: string;
    travelers?: string[];
    dayPlans?: DayPlanRequest[];
    notes?: string;
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
    category?: string;
    notes?: string;
}

export interface ItineraryResponse extends CreateItineraryRequest {
    id: string;
    createdAt: string;
    updatedAt: string;
}
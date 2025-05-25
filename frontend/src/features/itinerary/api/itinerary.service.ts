import apiClient from '../../../lib/api-client'; 
import type { CreateItineraryRequest, ItineraryResponse } from '../types/itinerary.types';

export const itineraryService = {
    createItinerary: async (itineraryData: CreateItineraryRequest): Promise<ItineraryResponse> => {
        const response = await apiClient.post<ItineraryResponse>('/itineraries', itineraryData);
        return response.data;
        
    },

    getItineraries: async (): Promise<ItineraryResponse[]> => {
        const response = await apiClient.get<ItineraryResponse[]>('/itineraries');
        return response.data;
    },

    getItineraryById: async (id: string): Promise<ItineraryResponse> => {
        const response = await apiClient.get<ItineraryResponse>(`/itineraries/${id}`);
        return response.data;
    },
};


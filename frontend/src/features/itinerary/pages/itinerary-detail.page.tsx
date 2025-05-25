import { useLocation, useParams } from 'react-router-dom';
import { useState, useEffect } from 'react';
import type { ItineraryResponse } from '../types/itinerary.types';
import { itineraryService } from '../api/itinerary.service';

const ItineraryDetailPage = () => {
    const location = useLocation();
    const { id } = useParams();
    const [itinerary, setItinerary] = useState<ItineraryResponse | null>(location.state || null);
    const [error, setError] = useState<string | null>(null);

    useEffect(() => {
        if (!itinerary && id) {
            itineraryService.getItineraryById(id)
                .then((response) => {
                    setItinerary(response);
                })
                .catch((error) => {
                    setError("Failed to fetch itinerary");
                    console.error("Error fetching itinerary:", error);
                });
        }
        
    }, [id, itinerary]);

    if (error) return <p className="text-red-600">{error}</p>
    if (!itinerary) return <p className="text-gray-500">Loading...</p>;

    return (
        <div className="max-w-3xl mx-auto py-8 px-4">
            <h1 className="text-3xl font-bold mb-4">{itinerary.destination}</h1>
            <p className="text-gray-500 mb-6">
                {itinerary.startDate} – {itinerary.endDate}
            </p>

            {itinerary.dayPlans?.map((day, index) => (
                <div key={index} className="mb-6">
                <h2 className="text-xl font-semibold mb-2">Day {index + 1}</h2>
                <ul className="space-y-2">
                    {day.activities.map((activity, idx) => (
                    <li key={idx} className="border p-3 rounded shadow-sm">
                        <p className="font-medium">{activity.name}</p>
                        <p className="text-sm text-gray-500">{activity.time}</p>
                        <p className="text-sm">{activity.location}</p>
                        {activity.notes && <p className="text-sm italic">{activity.notes}</p>}
                    </li>
                    ))}
                </ul>
                </div>
            ))}
        </div>
    )
}

export default ItineraryDetailPage;
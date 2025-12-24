import type { ActivityResponse } from "../types/itinerary.types";

const categoryMeta: Record<string, { label: string; emoji: string }> = {
  food: { label: "Food", emoji: "🍽️" },
  sightseeing: { label: "Sightseeing", emoji: "🗼" },
  activity: { label: "Activity", emoji: "🎟️" },
  transportation: { label: "Transport", emoji: "🚇" },
  other: { label: "Other", emoji: "📌" },
};

function formatTime(t?: string) {
  if (!t) return "—";
  return t;
}

const ActivityItem = ({ activity }: { activity: ActivityResponse }) => {
  const meta = categoryMeta[activity.category ?? "other"] ?? categoryMeta.other;

  return (
    <div className="relative pl-16">
      {/* timeline line */}
      <div className="absolute left-7 top-0 bottom-0 w-px bg-gray-200" />

      {/* time pill */}
      <div className="absolute left-0 top-4 w-14">
        <div className="text-xs font-medium text-gray-700 bg-white border border-gray-200 rounded-lg px-2 py-1 text-center shadow-sm">
          {formatTime(activity.time)}
        </div>
      </div>

      {/* node */}
      <div className="absolute left-[23px] top-6 h-3 w-3 rounded-full bg-blue-600 ring-4 ring-blue-100" />

      {/* card */}
      <div className="bg-white border border-gray-200 rounded-2xl shadow-sm px-4 py-3">
        <div className="flex items-start justify-between gap-3">
          <div className="min-w-0">
            <p className="font-semibold text-gray-900 truncate">
              {activity.name}
            </p>

            {activity.location && (
              <p className="text-sm text-gray-600 mt-0.5">{activity.location}</p>
            )}

            {activity.description && (
              <p className="text-sm text-gray-700 mt-2">{activity.description}</p>
            )}

            {activity.notes && (
              <p className="text-sm text-gray-500 mt-2 italic">{activity.notes}</p>
            )}

            <div className="mt-3 inline-flex items-center gap-2 text-xs font-medium text-gray-700 bg-gray-50 border border-gray-200 rounded-full px-3 py-1">
              <span aria-hidden>{meta.emoji}</span>
              <span>{meta.label}</span>
            </div>
          </div>

          {/* right-side icon placeholder (like your wireframe) */}
          <button
            type="button"
            className="shrink-0 h-10 w-10 rounded-xl border border-gray-200 bg-white hover:bg-gray-50 active:scale-[0.98] transition"
            title="Save"
            aria-label="Save activity"
            onClick={() => {
              // TODO: hook up "save" later if you want
            }}
          >
            <span className="text-lg">🔖</span>
          </button>
        </div>
      </div>
    </div>
  );
};

export default ActivityItem;

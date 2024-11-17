import Vue from "vue";
import VueRouter from "vue-router";
import DefaultLayout from "@/layouts/DefaultLayout.vue";
import Flights from  '@/pages/Flights.vue';
import FlightDetail from  '@/pages/FlightDetail.vue';
import Airplanes from  '@/pages/Airplanes.vue';
import AirplaneDetail from  '@/pages/AirplaneDetail.vue';
import Passengers from  '@/pages/Passengers.vue';
import PassengerDetail from  '@/pages/PassengerDetail.vue';
import Tickets from  '@/pages/Tickets.vue';
import TicketDetail from  '@/pages/TicketDetail.vue';
import Bookings from  '@/pages/Bookings.vue';
import BookingDetail from  '@/pages/BookingDetail.vue';
import Payments from  '@/pages/Payments.vue';
import PaymentDetail from  '@/pages/PaymentDetail.vue';
import Crews from  '@/pages/Crews.vue';
import CrewDetail from  '@/pages/CrewDetail.vue';
import CrewAssignments from  '@/pages/CrewAssignments.vue';
import CrewAssignmentDetail from  '@/pages/CrewAssignmentDetail.vue';
import Airports from  '@/pages/Airports.vue';
import AirportDetail from  '@/pages/AirportDetail.vue';
import Airlines from  '@/pages/Airlines.vue';
import AirlineDetail from  '@/pages/AirlineDetail.vue';
import Routes from  '@/pages/Routes.vue';
import RouteDetail from  '@/pages/RouteDetail.vue';
import Luggages from  '@/pages/Luggages.vue';
import LuggageDetail from  '@/pages/LuggageDetail.vue';
import Meals from  '@/pages/Meals.vue';
import MealDetail from  '@/pages/MealDetail.vue';
import InFlightServices from  '@/pages/InFlightServices.vue';
import InFlightServiceDetail from  '@/pages/InFlightServiceDetail.vue';
import RewardPrograms from  '@/pages/RewardPrograms.vue';
import RewardProgramDetail from  '@/pages/RewardProgramDetail.vue';
import Memberships from  '@/pages/Memberships.vue';
import MembershipDetail from  '@/pages/MembershipDetail.vue';
import LoungeAccesss from  '@/pages/LoungeAccesss.vue';
import LoungeAccessDetail from  '@/pages/LoungeAccessDetail.vue';
import Discounts from  '@/pages/Discounts.vue';
import DiscountDetail from  '@/pages/DiscountDetail.vue';
import Feedbacks from  '@/pages/Feedbacks.vue';
import FeedbackDetail from  '@/pages/FeedbackDetail.vue';
import Notifications from  '@/pages/Notifications.vue';
import NotificationDetail from  '@/pages/NotificationDetail.vue';

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "home",
    component: () => import("../views/HomeView.vue"),
			redirect: '/flights',
																				  },
  {
    path: "/pricing",
    name: "PricingView",
    component: () => import("../views/PricingView.vue"),
  },
  {
    path: "/arts-gallery",
    name: "ArtsGalleryView",
    component: () => import("../views/ArtsGalleryView.vue"),
  },
  {
    path: "/checkout/:id",
    name: "CheckoutView",
    component: () => import("../views/CheckoutView.vue"),
  },
  {
    path: "/stripe-checkout",
    name: "StripeCheckoutView",
    component: () => import("../views/StripeCheckoutView.vue"),
  },
	{
		path: '/flights',
		name: 'Flights',
		layout: DefaultLayout,
		component: Flights,
	},
	{
	    path: '/flight/:flightId', 
	    name: 'FlightDetail',
		layout: DefaultLayout,
	    component: FlightDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/airplanes',
		name: 'Airplanes',
		layout: DefaultLayout,
		component: Airplanes,
	},
	{
	    path: '/airplane/:airplaneId', 
	    name: 'AirplaneDetail',
		layout: DefaultLayout,
	    component: AirplaneDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/passengers',
		name: 'Passengers',
		layout: DefaultLayout,
		component: Passengers,
	},
	{
	    path: '/passenger/:passengerId', 
	    name: 'PassengerDetail',
		layout: DefaultLayout,
	    component: PassengerDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/tickets',
		name: 'Tickets',
		layout: DefaultLayout,
		component: Tickets,
	},
	{
	    path: '/ticket/:ticketId', 
	    name: 'TicketDetail',
		layout: DefaultLayout,
	    component: TicketDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/bookings',
		name: 'Bookings',
		layout: DefaultLayout,
		component: Bookings,
	},
	{
	    path: '/booking/:bookingId', 
	    name: 'BookingDetail',
		layout: DefaultLayout,
	    component: BookingDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/payments',
		name: 'Payments',
		layout: DefaultLayout,
		component: Payments,
	},
	{
	    path: '/payment/:paymentId', 
	    name: 'PaymentDetail',
		layout: DefaultLayout,
	    component: PaymentDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/crews',
		name: 'Crews',
		layout: DefaultLayout,
		component: Crews,
	},
	{
	    path: '/crew/:crewId', 
	    name: 'CrewDetail',
		layout: DefaultLayout,
	    component: CrewDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/crewAssignments',
		name: 'CrewAssignments',
		layout: DefaultLayout,
		component: CrewAssignments,
	},
	{
	    path: '/crewAssignment/:crewAssignmentId', 
	    name: 'CrewAssignmentDetail',
		layout: DefaultLayout,
	    component: CrewAssignmentDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/airports',
		name: 'Airports',
		layout: DefaultLayout,
		component: Airports,
	},
	{
	    path: '/airport/:airportId', 
	    name: 'AirportDetail',
		layout: DefaultLayout,
	    component: AirportDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/airlines',
		name: 'Airlines',
		layout: DefaultLayout,
		component: Airlines,
	},
	{
	    path: '/airline/:airlineId', 
	    name: 'AirlineDetail',
		layout: DefaultLayout,
	    component: AirlineDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/routes',
		name: 'Routes',
		layout: DefaultLayout,
		component: Routes,
	},
	{
	    path: '/route/:routeId', 
	    name: 'RouteDetail',
		layout: DefaultLayout,
	    component: RouteDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/luggages',
		name: 'Luggages',
		layout: DefaultLayout,
		component: Luggages,
	},
	{
	    path: '/luggage/:luggageId', 
	    name: 'LuggageDetail',
		layout: DefaultLayout,
	    component: LuggageDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/meals',
		name: 'Meals',
		layout: DefaultLayout,
		component: Meals,
	},
	{
	    path: '/meal/:mealId', 
	    name: 'MealDetail',
		layout: DefaultLayout,
	    component: MealDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/inFlightServices',
		name: 'InFlightServices',
		layout: DefaultLayout,
		component: InFlightServices,
	},
	{
	    path: '/inFlightService/:inFlightServiceId', 
	    name: 'InFlightServiceDetail',
		layout: DefaultLayout,
	    component: InFlightServiceDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/rewardPrograms',
		name: 'RewardPrograms',
		layout: DefaultLayout,
		component: RewardPrograms,
	},
	{
	    path: '/rewardProgram/:rewardProgramId', 
	    name: 'RewardProgramDetail',
		layout: DefaultLayout,
	    component: RewardProgramDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/memberships',
		name: 'Memberships',
		layout: DefaultLayout,
		component: Memberships,
	},
	{
	    path: '/membership/:membershipId', 
	    name: 'MembershipDetail',
		layout: DefaultLayout,
	    component: MembershipDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/loungeAccesss',
		name: 'LoungeAccesss',
		layout: DefaultLayout,
		component: LoungeAccesss,
	},
	{
	    path: '/loungeAccess/:loungeAccessId', 
	    name: 'LoungeAccessDetail',
		layout: DefaultLayout,
	    component: LoungeAccessDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/discounts',
		name: 'Discounts',
		layout: DefaultLayout,
		component: Discounts,
	},
	{
	    path: '/discount/:discountId', 
	    name: 'DiscountDetail',
		layout: DefaultLayout,
	    component: DiscountDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/feedbacks',
		name: 'Feedbacks',
		layout: DefaultLayout,
		component: Feedbacks,
	},
	{
	    path: '/feedback/:feedbackId', 
	    name: 'FeedbackDetail',
		layout: DefaultLayout,
	    component: FeedbackDetail,
	    props: true // Pass route params as props to the component
  	},
	{
		path: '/notifications',
		name: 'Notifications',
		layout: DefaultLayout,
		component: Notifications,
	},
	{
	    path: '/notification/:notificationId', 
	    name: 'NotificationDetail',
		layout: DefaultLayout,
	    component: NotificationDetail,
	    props: true // Pass route params as props to the component
  	},
];

const router = new VueRouter({
  mode: "hash",
  base: process.env.BASE_URL,
  routes,
});

export default router;

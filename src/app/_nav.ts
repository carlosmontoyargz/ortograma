interface NavAttributes {
	[propName: string]: any;
}

interface NavWrapper {
	attributes: NavAttributes;
	element: string;
}

interface NavBadge {
	text: string;
	variant: string;
}

interface NavLabel {
	class?: string;
	variant: string;
}

export interface NavData {
	name?: string;
	url?: string;
	icon?: string;
	badge?: NavBadge;
	title?: boolean;
	children?: NavData[];
	variant?: string;
	attributes?: NavAttributes;
	divider?: boolean;
	class?: string;
	label?: NavLabel;
	wrapper?: NavWrapper;
}

export const navItems: NavData[] = [
	/*{
		name: 'Dashboard',
		url: '/dashboard',
		icon: 'icon-speedometer',
		badge: {
			variant: 'info',
			text: 'NEW'
		}
	},*/
	{ title: true, name: 'Menú' },
	{
		name: 'Perfil',
		url: '/perfil',
		icon: 'cui-user'
	},
	{
		name: 'Lecciones',
		url: '/lecciones',
		icon: 'cui-note',
		children: [
			{
				name: 'Letras',
				url: '/lecciones/letras',
				icon: 'cui-lightbulb'
			},
			{
				name: 'Puntuación',
				url: '/lecciones/puntuacion',
				icon: 'cui-lightbulb'
			},
			{
				name: 'Acentuación',
				url: '/lecciones/acentuacion',
				icon: 'cui-lightbulb'
			},
		]
	},
	{
		title: true,
		name: 'Components'
	},
	{
		name: 'Base',
		url: '/base',
		icon: 'cui-lightbulb',
		children: [
			{
				name: 'Cards',
				url: '/base/cards',
				icon: 'cui-lightbulb'
			},
			{
				name: 'Carousels',
				url: '/base/carousels',
				icon: 'cui-lightbulb'
			},
			{
				name: 'Collapses',
				url: '/base/collapses',
				icon: 'cui-lightbulb'
			},
			{
				name: 'Forms',
				url: '/base/forms',
				icon: 'cui-lightbulb'
			},
			{
				name: 'Pagination',
				url: '/base/paginations',
				icon: 'cui-lightbulb'
			},
			{
				name: 'Popovers',
				url: '/base/popovers',
				icon: 'cui-lightbulb'
			},
			{
				name: 'Progress',
				url: '/base/progress',
				icon: 'cui-lightbulb'
			},
			{
				name: 'Switches',
				url: '/base/switches',
				icon: 'cui-lightbulb'
			},
			{
				name: 'Tables',
				url: '/base/tables',
				icon: 'cui-lightbulb'
			},
			{
				name: 'Tabs',
				url: '/base/tabs',
				icon: 'cui-lightbulb'
			},
			{
				name: 'Tooltips',
				url: '/base/tooltips',
				icon: 'cui-lightbulb'
			}
		]
	},
];

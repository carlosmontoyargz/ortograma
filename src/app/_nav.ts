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
];

<script>
	import Card from '$lib/admin/components/Card.svelte';
	import { SlideToggle, getModalStore } from '@skeletonlabs/skeleton';
	import { call } from '$lib/utils/service';
	import { onMount } from 'svelte';
	import TableAutoCard from '$lib/admin/components/TableAutoCard.svelte';
	import { usersList, usersStore } from '$lib/admin/stores/rolesStore';
	import Ico from '$lib/utils/Ico.svelte';
	import ButtonsContainer from '$lib/admin/components/ButtonsContainer.svelte';

	const modalStore = getModalStore();

	let selectRow = false;
	let selectedUsers = new Set();

	onMount(() => {
		usersList();
	});

	function DisplaySelectRow() {
		selectRow = !selectRow;
	}

	function toggleUserSelection(user) {
		if (selectedUsers.has(user)) {
			selectedUsers.delete(user);
		} else {
			selectedUsers.add(user);
		}
	}

	async function deleteUsersRoles(username) {
		const formData = new FormData();
		formData.append('username', username);
		try {
			//@ts-ignore
			const res = await call('roles.Delete', formData);
			if (res?.admin?.response?.['@_state'] == 'success') {
				usersList();
				modalStore.close();
			}
		} catch (error) {
			console.error('Error deleting user role:', error);
		}
	}

	async function deleteAllRoles() {
		try {
			const res = await call('roles.DeleteAll');
			console.log('service delete All roles', res);
			if (res?.admin?.response?.['@_state'] == 'success') {
				usersList();
				modalStore.close();
			}
		} catch (err) {
			console.error(err);
		}
	}

	function openModals(mode, row) {
		modalStore.trigger({
			type: 'component',
			component: 'modalRoles',
			meta: { mode, row },
			title: row ? `Edit roles` : `New roles`
		});
	}

	function openDeleteAllModal() {
		modalStore.trigger({
			type: 'component',
			component: 'modalWarning',
			title: 'You are going to delete All Roles',
			body: 'Are you sure you want to ?',
			meta: { mode: 'Confirm' },
			response: async (confirmed) => {
				if (confirmed) {
					deleteAllRoles();
				}
			}
		});
	}

	function openDeleteModal(row) {
		modalStore.trigger({
			type: 'component',
			component: 'modalWarning',
			title: 'Please Confirm',
			body: 'Are you sure you want to delete the role ?',
			meta: { mode: 'Confirm' },
			response: (confirmed) => {
				if (confirmed) {
					deleteUsersRoles(row);
				}
			}
		});
	}

	function getTailwindClass(role) {
		role = role.toUpperCase();
		if (role.endsWith('VIEW')) {
			return 'bg-green-500 mt-2 mr-5 ml-5';
		} else if (role.endsWith('CONFIG')) {
			return 'bg-blue-500 mt-2 mr-5 ml-5';
		} else {
			return 'bg-yellow-500 mt-2 mr-5 ml-5';
		}
	}

	function exportUserFile() {
		const usersArray = Array.from(selectedUsers);
		const jsonString = `data:text/json;chatset=utf-8,${encodeURIComponent(
			JSON.stringify(usersArray)
		)}`;
		const link = document.createElement('a');
		link.href = jsonString;
		link.download = 'users_export.json';

		link.click();
	}

	const userActions = {
		add: {
			name: 'Add User',
			icon: 'grommet-icons:add'
		},
		import: {
			name: 'Import Users',
			icon: 'bytesize:import'
		}
		// export: {
		// 	name: 'Export Users',
		// 	icon: 'bytesize:export'
		// }
	};
</script>

<Card title="Roles">
	<div slot="cornerOption">
		<button class="delete-button" on:click={openDeleteAllModal}>
			<Ico icon="mingcute:delete-line" />
			<p>Delete All Roles</p>
		</button>
	</div>
	<ButtonsContainer class="mb-10">
		{#each Object.entries(userActions) as [type, { name, icon }]}
			<button class="basic-button" on:click={() => openModals(type)}>
				<p>{name}</p>
				<Ico {icon} />
			</button>
		{/each}

		<button class={selectRow ? 'delete-button' : 'basic-button'} on:click={DisplaySelectRow}>
			<p>{selectRow ? 'Cancel Export' : 'Export Users'}</p>
			<Ico icon={selectRow ? 'material-symbols-light:cancel-outline' : 'bytesize:export'} />
		</button>

		{#if selectRow}
			<button class="yellow-button" on:click={exportUserFile}>
				<p>Validate export</p>
				<Ico icon="bytesize:export" />
			</button>
		{/if}
	</ButtonsContainer>

	{#if $usersStore.length >= 0}
		<TableAutoCard
			definition={[
				{ name: 'Export', custom: true },
				{ name: 'Name', key: 'name' },
				{ name: 'Role', key: 'role', custom: true },
				{ name: 'Edit', custom: true },
				{ name: 'Delete', custom: true }
			].filter((elt) => selectRow || elt.name != 'Export')}
			data={$usersStore}
			let:row
			let:def
		>
			{#if def.name === 'Role'}
				{#each row.role as role}
					{role.replace(/_/g, ' ')}
				{/each}
			{:else if def.name === 'Edit'}
				<button class="yellow-button" on:click={() => openModals('add', row)}>
					<Ico icon="mdi:edit-outline" />
				</button>
			{:else if def.name === 'Delete'}
				<button class="delete-button" on:click={() => openDeleteModal(row.name)}>
					<Ico icon="mingcute:delete-line" />
				</button>
			{:else if def.name === 'Export'}
				<!-- <SlideToggle
					active="min-w-12 bg-success-400 dark:bg-success-700"
					background="min-w-12 bg-error-400 dark:bg-error-700"
					name="slide"
					bind:checked={value}
					size="sm"
				/> -->
				<input type="checkbox" on:change={() => toggleUserSelection(row)} />
			{/if}
		</TableAutoCard>
	{:else}
		<div class="table-container">
			<table class="rounded-token table">
				<thead class="rounded-token">
					<tr>
						{#each Array(5) as _}
							<th class="header dark:bg-surface-800">
								<div class="my-2 h-8 placeholder animate-pulse"></div>
							</th>
						{/each}
					</tr>
				</thead>
				<tbody>
					{#each Array(5) as _}
						<tr>
							{#each Array(5) as _}
								<td>
									<div class="my-2 h-8 placeholder animate-pulse"></div>
								</td>
							{/each}
						</tr>
					{/each}
				</tbody>
			</table>
		</div>
	{/if}
</Card>
